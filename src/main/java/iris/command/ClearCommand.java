package iris.command;

import iris.Storage;
import iris.TaskList;
import iris.Ui;
import iris.exception.InvalidCommandException;
import iris.exception.IrisException;

/**
 * Represents a command to clear the task list.
 */
public class ClearCommand extends Command {

    public ClearCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IrisException {
        if (taskList.getNumTasks() == 0) {
            throw new InvalidCommandException("There are no tasks right now.");
        }

        taskList.clearTasks();
        storage.store(taskList.asList());

        ui.showMessage("Cleared! There are no more tasks in the list.");
    }
}
