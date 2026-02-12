package iris;

import java.util.ArrayList;
import java.util.List;

import iris.exception.InvalidInputException;
import iris.task.Task;

/**
 * An object used to manage a list of tasks.
 * <br>
 * This class provides methods to perform task operations.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task to this task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes task from this task list.
     *
     * @param index Index of task to be deleted.
     * @return Task that was deleted.
     * @throws InvalidInputException If an invalid index was provided.
     */
    public Task deleteTask(int index) throws InvalidInputException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidInputException("Index must be from 1 to " + tasks.size() + ".");
        }
        return tasks.remove(index - 1);
    }

    /**
     * Clears this task list.
     */
    public void clearTasks() {
        tasks.clear();
    }

    /**
     * Returns all tasks in this task list which match the given description.
     *
     * @param description Task description string.
     * @return TaskList containing matching tasks.
     */
    public TaskList findTasks(String description) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Retrieves a task from this task list.
     *
     * @param index Index of task to be retrieved.
     * @return Task that was retrieved.
     * @throws InvalidInputException If an invalid index was provided.
     */
    public Task getTask(int index) throws InvalidInputException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidInputException("Index must be from 1 to " + tasks.size() + ".");
        }
        return tasks.get(index - 1);
    }

    /**
     * Returns the number of tasks in this task list.
     *
     * @return Number of tasks in the list.
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Returns this task list as a List.
     *
     * @return List object.
     */
    public List<Task> asList() {
        return tasks;
    }

    /**
     * Returns this task count in sentence format.
     *
     * @return Task count string.
     */
    public String toTaskCountFormat() {
        int numTasks = tasks.size();
        return numTasks == 1
                ? "Now you have 1 task in the list."
                : "Now you have " + numTasks + " tasks in the list.";
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
