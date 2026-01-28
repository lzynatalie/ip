package iris;

import iris.exception.InvalidInputException;
import iris.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws InvalidInputException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidInputException("Index must be from 1 to " + tasks.size() + ".");
        }
        return tasks.remove(index - 1);
    }

    public void clearTasks() {
        tasks.clear();
    }

    public Task getTask(int index) throws InvalidInputException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidInputException("Index must be from 1 to " + tasks.size() + ".");
        }
        return tasks.get(index - 1);
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public List<Task> asList() {
        return tasks;
    }

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
