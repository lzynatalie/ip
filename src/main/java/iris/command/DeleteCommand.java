package iris.command;

import iris.Storage;
import iris.TaskList;
import iris.exception.InvalidCommandException;
import iris.exception.InvalidInputException;
import iris.exception.IrisException;
import iris.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws IrisException {
        int numTasks = taskList.getNumTasks();

        if (numTasks == 0) {
            throw new InvalidCommandException("There are no tasks right now.");
        }
        if (index < 1 || index > numTasks) {
            throw new InvalidInputException("Index must be from 1 to " + numTasks + ".");
        }

        Task task = taskList.deleteTask(index);
        storage.store(taskList.asList());

        return "Noted. I've removed this task:\n   " + task + "\n" + taskList.toTaskCountFormat();
    }
}
