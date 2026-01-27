import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Iris {
    private Ui ui;

    public Iris() {
        ui = new Ui();
    }

    public void run() {
        Path filePath = Path.of("./data/iris.txt");
        TaskList taskList = initialiseTaskList(filePath);

        ui.showWelcome();

        String userInput = ui.readCommand();
        while (true) {
            String[] strings = userInput.split(" ", 2);
            String command = strings[0];
            String input = "";
            if (strings.length > 1) {
                input = strings[1];
            }

            if (command.equals("bye")) {
                break;
            }

            try {
                handleCommand(filePath, taskList, command, input);
            } catch (InvalidCommandException | InvalidInputException e) {
                ui.showError(e.getMessage());
            }

            ui.showHorizontalLine();
            userInput = ui.readCommand();
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Iris().run();
    }

    private void handleCommand(Path filePath, TaskList taskList, String command, String input)
            throws InvalidCommandException, InvalidInputException {
        switch (command) {
            case "list" -> listTasks(taskList);
            case "mark" -> markTask(taskList, input, true);
            case "unmark" -> markTask(taskList, input, false);
            case "todo" -> addTask(taskList, TaskType.TODO, input);
            case "deadline" -> addTask(taskList, TaskType.DEADLINE, input);
            case "event" -> addTask(taskList, TaskType.EVENT, input);
            case "delete" -> deleteTask(taskList, input);
            case "clear" -> clearTasks(taskList);
            default -> throw new InvalidCommandException();
        }

        String saveData = taskList.toSaveDataFormat();
        try {
            Files.writeString(filePath, saveData);
        } catch (IOException e) {
            ui.showError("An error has occurred: " + e.getMessage());
        }
    }

    private void listTasks(TaskList taskList) throws InvalidCommandException {
        int numTasks = taskList.getNumTasks();
        if (numTasks == 0) {
            throw new InvalidCommandException("There are no tasks right now.");
        }
        ui.showMessage("Here are the tasks in your list:\n" + taskList);
    }

    private void addTask(TaskList taskList, TaskType taskType, String input) throws InvalidInputException {
        Task task = null;
        switch (taskType) {
            case TODO -> task = addToDoTask(taskList, input);
            case DEADLINE -> task = addDeadlineTask(taskList, input);
            case EVENT -> task = addEventTask(taskList, input);
        }
        ui.showMessage("Got it. I've added this task:\n   " + task);
        printNumTasks(taskList);
    }

    private Task addToDoTask(TaskList taskList, String input) throws InvalidInputException {
        String description = input.strip();
        try {
            return taskList.addToDoTask(description);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    private Task addDeadlineTask(TaskList taskList, String input) throws InvalidInputException {
        String[] deadlineParts = input.split("/", 2);

        if (deadlineParts.length < 2 || !deadlineParts[1].startsWith("by ")) {
            throw new InvalidInputException("Please provide a deadline.");
        }

        String description = deadlineParts[0].strip();
        String by = deadlineParts[1].split(" ", 2)[1].strip();

        try {
            return taskList.addDeadlineTask(description, by);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    private Task addEventTask(TaskList taskList, String input) throws InvalidInputException {
        String[] eventParts = input.split("/", 3);

        if (eventParts.length < 3 || !eventParts[1].startsWith("from ") || !eventParts[2].startsWith("to ")) {
            throw new InvalidInputException("Please provide a start and end time.");
        }

        String description = eventParts[0].strip();
        String from = eventParts[1].split(" ", 2)[1].strip();
        String to = eventParts[2].split(" ", 2)[1].strip();

        try {
            return taskList.addEventTask(description, from, to);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    private void markTask(TaskList taskList, String input, boolean isDone)
            throws InvalidCommandException, InvalidInputException {
        int index;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please provide a valid index.");
        }

        try {
            Task task = taskList.markTask(index, isDone);
            if (isDone) {
                ui.showMessage("Nice! I've marked this task as done:\n   " + task);
            } else {
                ui.showMessage("OK, I've marked this task as not done yet:\n   " + task);
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    private void deleteTask(TaskList taskList, String input)
            throws InvalidCommandException, InvalidInputException {
        int index;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please provide a valid index.");
        }

        try {
            Task task = taskList.deleteTask(index);
            ui.showMessage("Noted. I've removed this task:\n   " + task);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }

        printNumTasks(taskList);
    }

    private void clearTasks(TaskList taskList) throws InvalidCommandException {
        taskList.clearTasks();
        ui.showMessage("Cleared! There are no more tasks in the list.");
    }

    private void printNumTasks(TaskList taskList) {
        int numTasks = taskList.getNumTasks();
        String message = numTasks == 1
                ? "Now you have 1 task in the list."
                : "Now you have " + numTasks + " tasks in the list.";
        ui.showMessage(message);
    }

    private TaskList initialiseTaskList(Path filePath) {
        Path directoryPath = filePath.getParent();
        TaskList taskList = new TaskList();

        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(filePath.getParent());
            } catch (IOException e) {
                ui.showError("An error has occurred: " + e.getMessage());
            }
        }

        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                ui.showError("An error has occurred: " + e.getMessage());
            }
            return taskList;
        }

        try {
            Scanner fileReader = new Scanner(filePath);
            while (fileReader.hasNext()) {
                String task = fileReader.nextLine();
                String[] taskParts = task.split(" \\| ");
                boolean isDone = taskParts[1].equals("1");
                switch (taskParts[0]) {
                    case "T" -> taskList.addToDoTask(taskParts[2], isDone);
                    case "D" -> taskList.addDeadlineTask(taskParts[2], isDone, taskParts[3]);
                    case "E" -> taskList.addEventTask(taskParts[2], isDone, taskParts[3], taskParts[4]);
                }
            }
        } catch (IOException e) {
            ui.showError("An error has occurred: " + e.getMessage());
        }

        return taskList;
    }
}
