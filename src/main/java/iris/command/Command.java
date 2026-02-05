package iris.command;

import iris.Storage;
import iris.TaskList;
import iris.Ui;
import iris.exception.IrisException;

/**
 * Represents an executable user command.
 */
public abstract class Command {

    /**
     * Executes this command.
     *
     * @param taskList Task list to perform task operations on.
     * @param ui Ui object to display outputs.
     * @param storage Storage object to store tasks.
     * @throws IrisException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IrisException {
        ui.showMessage(execute(taskList, storage));
    }

    public abstract String execute(TaskList taskList, Storage storage) throws IrisException;

    public boolean isExit() {
        return false;
    }
}
