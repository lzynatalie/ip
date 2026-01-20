import java.util.Arrays;

public class TaskList {
    private Task[] tasks = new Task[100];
    private int numTasks = 0;

    public TaskList() {}

    public Task addTask(TaskType taskType, String taskDescription) throws InvalidInputException {
        Task task = new Task();
        switch (taskType) {
        case TODO:
            taskDescription = taskDescription.strip();
            if (taskDescription.isEmpty()) {
                throw new InvalidInputException("The description of a TODO cannot be empty.");
            }
            task = new ToDoTask(taskDescription);
            break;
        case DEADLINE:
            String[] deadlineParts = taskDescription.split("/", 2);
            if (deadlineParts[0].isEmpty()) {
                throw new InvalidInputException("The description of a DEADLINE cannot be empty.");
            } else if (deadlineParts.length < 2 || deadlineParts[1].isEmpty() || !deadlineParts[1].startsWith("by ")) {
                throw new InvalidInputException("deadline [description] /by [deadline]");
            }
            taskDescription = deadlineParts[0].strip();
            String by = deadlineParts[1].split(" ", 2)[1].strip();
            if (by.isEmpty()) {
                throw new InvalidInputException("Please provide a deadline.");
            }
            task = new DeadlineTask(taskDescription, by);
            break;
        case EVENT:
            String[] eventParts = taskDescription.split("/", 3);
            if (eventParts[0].isEmpty()) {
                throw new InvalidInputException("The description of a EVENT cannot be empty.");
            } else if (eventParts.length < 3 || eventParts[1].isEmpty() || eventParts[2].isEmpty()
                    || !(eventParts[1].startsWith("from ") && eventParts[2].startsWith("to "))) {
                throw new InvalidInputException("event [description] /from [start] /to [end]");
            }
            taskDescription = eventParts[0].strip();
            String from = eventParts[1].split(" ", 2)[1].strip();
            String to = eventParts[2].split(" ", 2)[1].strip();
            if (from.isEmpty() || to.isEmpty()) {
                throw new InvalidInputException("Please provide a start and end time.");
            }
            task = new EventTask(taskDescription, from, to);
            break;
        default:
            break;
        }
        tasks[numTasks] = task;
        numTasks++;
        return task;
    }

    public void markTask(int index, boolean isDone) {
        if (isDone) {
            tasks[index - 1].markAsDone();
        } else {
            tasks[index - 1].markAsUndone();
        }
    }

    public Task getTask(int index) {
        return tasks[index - 1];
    }

    public int getNumTasks() {
        return numTasks;
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < numTasks; i++) {
            string = string.concat((i + 1) + ". " + tasks[i] + "\n");
        }
        string = string.strip();
        return string;
    }
}
