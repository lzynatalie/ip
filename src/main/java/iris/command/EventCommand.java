package iris.command;

import iris.*;
import iris.exception.InvalidInputException;
import iris.exception.IrisException;
import iris.task.EventTask;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IrisException {
        if (description.isEmpty()) {
            throw new InvalidInputException("The description of an EVENT cannot be empty.");
        }
        if (from.isEmpty()) {
            throw new InvalidInputException("The start time of an EVENT cannot be empty.");
        }
        if (to.isEmpty()) {
            throw new InvalidInputException("The end time of an EVENT cannot be empty.");
        }

        EventTask task = new EventTask(description, StringFormatter.toLocalDateTime(from),
                StringFormatter.toLocalDateTime(to));
        taskList.addTask(task);
        storage.store(taskList.asList());

        ui.showMessage("Got it. I've added this task:\n   " + task + "\n" + taskList.toTaskCountFormat());
    }
}
