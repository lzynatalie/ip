package iris.command;

import iris.Storage;
import iris.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
