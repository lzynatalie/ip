package iris.task;

import iris.StringFormatter;

import java.time.LocalDateTime;

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
