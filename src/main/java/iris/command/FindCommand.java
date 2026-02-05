package iris.command;

import iris.Storage;
import iris.TaskList;
import iris.exception.InvalidCommandException;
import iris.exception.InvalidInputException;
import iris.exception.IrisException;

/**
 * Represents a command to find a task in the task list.
 */
public class FindCommand extends Command {
    private String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws IrisException {
        if (description.isEmpty()) {
            throw new InvalidInputException("Please provide a valid description.");
        }
        if (taskList.getNumTasks() == 0) {
            throw new InvalidCommandException("There are no tasks right now.");
        }

        TaskList tasks = taskList.findTasks(description);

        if (tasks.getNumTasks() == 0) {
            return "There are no tasks containing the description \"" + description + "\".";
        } else {
            return "Here are the matching tasks in your list:\n" + tasks;
        }
    }
}
