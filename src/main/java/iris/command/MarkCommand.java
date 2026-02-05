package iris.command;

import iris.Storage;
import iris.TaskList;
import iris.Ui;
import iris.exception.InvalidCommandException;
import iris.exception.IrisException;
import iris.task.Task;

/**
 * Represents a command to mark a task from the task list as complete.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IrisException {
        if (taskList.getNumTasks() == 0) {
            throw new InvalidCommandException("There are no tasks right now.");
        }

        Task task = taskList.getTask(index);
        if (task.isDone()) {
            throw new InvalidCommandException("Task is already marked.");
        }

        task.markAsDone();
        storage.store(taskList.asList());

        ui.showMessage("Nice! I've marked this task as done:\n   " + task);
    }
}
