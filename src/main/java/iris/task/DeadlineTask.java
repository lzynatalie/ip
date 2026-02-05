package iris.task;

import java.time.LocalDateTime;

import iris.StringFormatter;

/**
 * A task that needs to be done before a specific date/time.
 */
public class DeadlineTask extends Task {
    private LocalDateTime by;

    public DeadlineTask(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    public DeadlineTask(String description, LocalDateTime by) {
        this(description, false, by);
    }

    @Override
    public String toSaveDataFormat() {
        String status = super.isDone() ? "1" : "0";
        return "D | " + status + " | " + super.getDescription() + " | " + StringFormatter.toInputDateTimeFormat(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + StringFormatter.toOutputDateTimeFormat(by) + ")";
    }
}
