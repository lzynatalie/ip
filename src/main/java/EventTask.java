public class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public EventTask(String description, String from, String to) {
        this(description, false, from, to);
    }

    @Override
    public String toSaveFileFormat() {
        String status = super.isDone() ? "1" : "0";
        return "E | " + status + " | " + super.getDescription() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
