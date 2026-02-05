package iris.command;

import iris.Storage;
import iris.TaskList;
import iris.Ui;
import iris.exception.InvalidInputException;
import iris.exception.IrisException;
import iris.task.ToDoTask;

/**
 * Represents a command to add a to-do task to the task list.
 */
public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IrisException {
        if (description.isEmpty()) {
            throw new InvalidInputException("The description of a TODO cannot be empty.");
        }

        ToDoTask task = new ToDoTask(description);
        taskList.addTask(task);
        storage.store(taskList.asList());

        ui.showMessage("Got it. I've added this task:\n   " + task + "\n" + taskList.toTaskCountFormat());
    }
}
