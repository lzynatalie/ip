package iris.command;

import iris.exception.InvalidCommandException;
import iris.Storage;
import iris.TaskList;
import iris.Ui;

public class UnknownCommand extends Command {

    public UnknownCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException();
    }
}
