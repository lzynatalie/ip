package iris.task;

import iris.StringFormatter;

import java.time.LocalDateTime;

public class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public EventTask(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        this(description, false, from, to);
    }

    @Override
    public String toSaveDataFormat() {
        String status = super.isDone() ? "1" : "0";
        return "E | " + status + " | " + super.getDescription() + " | "
                + StringFormatter.toInputDateTimeFormat(from) + " | " + StringFormatter.toInputDateTimeFormat(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + StringFormatter.toOutputDateTimeFormat(from) + " to: " + StringFormatter.toOutputDateTimeFormat(to) + ")";
    }
}
