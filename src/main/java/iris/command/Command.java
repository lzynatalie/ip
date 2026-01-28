package iris.command;

import iris.exception.IrisException;
import iris.Storage;
import iris.TaskList;
import iris.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IrisException;

    public boolean isExit() {
        return false;
    }
}
