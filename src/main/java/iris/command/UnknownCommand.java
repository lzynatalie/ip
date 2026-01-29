package iris.command;

import iris.exception.InvalidCommandException;
import iris.Storage;
import iris.TaskList;
import iris.Ui;

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
