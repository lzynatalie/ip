package iris.command;

import iris.Storage;
import iris.TaskList;
import iris.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getNumTasks() == 0) {
            ui.showMessage("There are no tasks right now.");
        } else {
            ui.showMessage("Here are the tasks in your list:\n" + taskList);
        }
    }
}
