import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public Task addTask(TaskType taskType, String taskDescription) throws InvalidInputException {
        Task task;
        switch (taskType) {
            case TODO -> task = addToDoTask(taskDescription);
            case DEADLINE -> task = addDeadlineTask(taskDescription);
            case EVENT -> task = addEventTask(taskDescription);
            default -> task = new Task();
        }
        tasks.add(task);
        return task;
    }

    private Task addToDoTask(String taskDescription) throws InvalidInputException {
        String description = taskDescription.strip();
        if (description.isEmpty()) {
            throw new InvalidInputException("The description of a TODO cannot be empty.");
        }
        return new ToDoTask(description);
    }

    private Task addDeadlineTask(String taskDescription) throws InvalidInputException {
        String[] deadlineParts = taskDescription.split("/", 2);

        if (deadlineParts[0].isEmpty()) {
            throw new InvalidInputException("The description of a DEADLINE cannot be empty.");
        } else if (deadlineParts.length < 2 || deadlineParts[1].isEmpty() || !deadlineParts[1].startsWith("by ")) {
            throw new InvalidInputException("deadline [description] /by [deadline]");
        }

        String description = deadlineParts[0].strip();
        String by = deadlineParts[1].split(" ", 2)[1].strip();

        if (by.isEmpty()) {
            throw new InvalidInputException("Please provide a deadline.");
        }

        return new DeadlineTask(description, by);
    }

    private Task addEventTask(String taskDescription) throws InvalidInputException {
        String[] eventParts = taskDescription.split("/", 3);

        if (eventParts[0].isEmpty()) {
            throw new InvalidInputException("The description of a EVENT cannot be empty.");
        } else if (eventParts.length < 3 || eventParts[1].isEmpty() || eventParts[2].isEmpty()
                || !(eventParts[1].startsWith("from ") && eventParts[2].startsWith("to "))) {
            throw new InvalidInputException("event [description] /from [start] /to [end]");
        }

        String description = eventParts[0].strip();
        String from = eventParts[1].split(" ", 2)[1].strip();
        String to = eventParts[2].split(" ", 2)[1].strip();

        if (from.isEmpty() || to.isEmpty()) {
            throw new InvalidInputException("Please provide a start and end time.");
        }

        return new EventTask(description, from, to);
    }

    public Task markTask(int index, boolean isDone) {
        Task task = tasks.get(index - 1);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        return task;
    }

    public Task deleteTask(int index) {
        return tasks.remove(index - 1);
    }

    public int getNumTasks() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < tasks.size(); i++) {
            string = string.concat((i + 1) + ". " + tasks.get(i) + "\n");
        }
        string = string.strip();
        return string;
    }
}
