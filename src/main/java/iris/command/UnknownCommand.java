package iris.command;

import iris.Storage;
import iris.TaskList;
import iris.exception.InvalidCommandException;

/**
 * Represents a command that does not exist in the program.
 */
public class UnknownCommand extends Command {

    public UnknownCommand() {}

    @Override
    public String execute(TaskList taskList, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException();
    }
}
