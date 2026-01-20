import java.util.Scanner;

public class Iris {
    public static final String CHATBOT_NAME = "Iris";
    public static final String HORIZONTAL_LINE = "⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻";

    public static void main(String[] args) {
        String welcomeMessage = HORIZONTAL_LINE + "\n"
                + "Hello! I'm " + CHATBOT_NAME + ".\n"
                + "What can I do for you?\n"
                + HORIZONTAL_LINE;
        String goodbyeMessage = "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE;
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        System.out.println(welcomeMessage);
        String userInput = scanner.nextLine();
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
                handleCommand(command, taskList, input);
            } catch (InvalidCommandException | InvalidInputException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(HORIZONTAL_LINE);
            userInput = scanner.nextLine();
        }
        System.out.println(goodbyeMessage);
    }

    private static void handleCommand(String command, TaskList taskList, String input)
            throws InvalidCommandException, InvalidInputException {
        switch (command) {
            case "list" -> listTasks(taskList);
            case "mark" -> markTask(taskList, input, true);
            case "unmark" -> markTask(taskList, input, false);
            case "todo" -> addTask(taskList, TaskType.TODO, input);
            case "deadline" -> addTask(taskList, TaskType.DEADLINE, input);
            case "event" -> addTask(taskList, TaskType.EVENT, input);
            case "delete" -> deleteTask(taskList, input);
            default -> throw new InvalidCommandException();
        }
    }

    private static void listTasks(TaskList taskList) throws InvalidCommandException {
        int numTasks = taskList.getNumTasks();
        if (numTasks == 0) {
            throw new InvalidCommandException("There are no tasks right now.");
        }
        System.out.println("Here are the tasks in your list:\n" + taskList);
    }

    private static void addTask(TaskList taskList, TaskType taskType, String input) throws InvalidInputException {
        if (input.isEmpty()) {
            throw new InvalidInputException("The description of a " + taskType.name() + " cannot be empty.");
        }

        Task task;
        switch (taskType) {
            case TODO -> task = taskList.addTask(TaskType.TODO, input);
            case DEADLINE -> task = taskList.addTask(TaskType.DEADLINE, input);
            case EVENT -> task = taskList.addTask(TaskType.EVENT, input);
            default -> task = new Task();
        }
        System.out.println("Got it. I've added this task:\n   " + task);

        int numTasks = taskList.getNumTasks();
        printNumTasks(numTasks);
    }

    private static void markTask(TaskList taskList, String input, boolean isDone)
            throws InvalidCommandException, InvalidInputException {
        int numTasks = taskList.getNumTasks();
        if (numTasks == 0) {
            throw new InvalidCommandException("There are no tasks right now.");
        }

        int index;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please provide a valid index.");
        }

        if (index < 1 || index > numTasks) {
            throw new InvalidInputException("Index must be from 1 to " + numTasks + ".");
        }

        Task task = taskList.markTask(index, isDone);

        if (isDone) {
            System.out.println("Nice! I've marked this task as done:\n   " + task);
        } else {
            System.out.println("OK, I've marked this task as not done yet:\n   " + task);
        }
    }

    public static void deleteTask(TaskList taskList, String input)
            throws InvalidCommandException, InvalidInputException {
        int numTasks = taskList.getNumTasks();
        if (numTasks == 0) {
            throw new InvalidCommandException("There are no tasks right now.");
        }

        int index;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please provide a valid index.");
        }

        if (index < 1 || index > numTasks) {
            throw new InvalidInputException("Index must be from 1 to " + numTasks + ".");
        }

        Task task = taskList.deleteTask(index);
        System.out.println("Noted. I've removed this task:\n   " + task);

        printNumTasks(numTasks - 1);
    }

    public static void printNumTasks(int numTasks) {
        String message = numTasks == 1
                ? "Now you have 1 task in the list."
                : "Now you have " + numTasks + " tasks in the list.";
        System.out.println(message);
    }
}
