package iris.command;

import iris.Storage;
import iris.StringFormatter;
import iris.TaskList;
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

    /**
     * Initialises an event command.
     *
     * @param description Description of the event task.
     * @param from Start time of the event task.
     * @param to End time of the event task.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws IrisException {
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

        return "Got it. I've added this task:\n   " + task + "\n" + taskList.toTaskCountFormat();
    }
}
