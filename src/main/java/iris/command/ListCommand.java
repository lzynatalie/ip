package iris.command;

import iris.Storage;
import iris.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public String execute(TaskList taskList, Storage storage) {
        if (taskList.getNumTasks() == 0) {
            return "There are no tasks right now.";
        } else {
            return "Here are the tasks in your list:\n" + taskList;
        }
    }
}
