package iris.command;

import iris.Storage;
import iris.TaskList;
import iris.Ui;
import iris.exception.InvalidCommandException;

/**
 * Represents a command that does not exist in the program.
 */
public class UnknownCommand extends Command {

    public UnknownCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException();
    }
}
