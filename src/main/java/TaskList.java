import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public ToDoTask addToDoTask(String taskDescription) throws IllegalArgumentException {
        return addToDoTask(taskDescription, false);
    }

    public ToDoTask addToDoTask(String taskDescription, boolean isDone) throws IllegalArgumentException {
        if (taskDescription.isEmpty()) {
            throw new IllegalArgumentException("The description of a TODO cannot be empty.");
        }
        ToDoTask task = new ToDoTask(taskDescription, isDone);
        tasks.add(task);
        return task;
    }

    public DeadlineTask addDeadlineTask(String taskDescription, String by) throws IllegalArgumentException {
        return addDeadlineTask(taskDescription, false, by);
    }

    public DeadlineTask addDeadlineTask(String taskDescription, boolean isDone, String by)
            throws IllegalArgumentException {
        if (taskDescription.isEmpty()) {
            throw new IllegalArgumentException("The description of a DEADLINE cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new IllegalArgumentException("The deadline of a DEADLINE cannot be empty.");
        }
        DeadlineTask task = new DeadlineTask(taskDescription, isDone, StringFormatter.toLocalDateTime(by));
        tasks.add(task);
        return task;
    }

    public EventTask addEventTask(String taskDescription, String from, String to) throws IllegalArgumentException {
        return addEventTask(taskDescription, false, from, to);
    }

    public EventTask addEventTask(String taskDescription, boolean isDone, String from, String to)
            throws IllegalArgumentException {
        if (taskDescription.isEmpty()) {
            throw new IllegalArgumentException("The description of an EVENT cannot be empty.");
        }
        if (from.isEmpty()) {
            throw new IllegalArgumentException("The start time of an EVENT cannot be empty.");
        }
        if (to.isEmpty()) {
            throw new IllegalArgumentException("The end time of an EVENT cannot be empty.");
        }
        EventTask task = new EventTask(taskDescription, isDone,
                StringFormatter.toLocalDateTime(from), StringFormatter.toLocalDateTime(to));
        tasks.add(task);
        return task;
    }

    public Task markTask(int index, boolean isDone) throws InvalidCommandException, IllegalArgumentException {
        if (tasks.isEmpty()) {
            throw new InvalidCommandException("There are no tasks right now.");
        }
        if (index < 1 || index > tasks.size()) {
            throw new IllegalArgumentException("Index must be from 1 to " + tasks.size() + ".");
        }

        Task task = tasks.get(index - 1);
        if (isDone && task.isDone()) {
            throw new InvalidCommandException("Task is already marked.");
        }
        if (!isDone && !task.isDone()) {
            throw new InvalidCommandException("Task is already unmarked.");
        }

        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        return task;
    }

    public Task deleteTask(int index) throws InvalidCommandException, IllegalArgumentException {
        if (tasks.isEmpty()) {
            throw new InvalidCommandException("There are no tasks right now.");
        }
        if (index < 1 || index > tasks.size()) {
            throw new IllegalArgumentException("Index must be from 1 to " + tasks.size() + ".");
        }
        return tasks.remove(index - 1);
    }

    public void clearTasks() throws InvalidCommandException {
        if (tasks.isEmpty()) {
            throw new InvalidCommandException("There are no tasks right now.");
        }
        tasks.clear();
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public String toSaveDataFormat() {
        String string = "";
        for (Task task : tasks) {
            string = string.concat(task.toSaveDataFormat() + "\n");
        }
        return string;
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
