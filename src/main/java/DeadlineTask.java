public class DeadlineTask extends Task {
    private String by;

    public DeadlineTask(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public DeadlineTask(String description, String by) {
        this(description, false, by);
    }

    @Override
    public String toSaveFileFormat() {
        String status = super.isDone() ? "1" : "0";
        return "D | " + status + " | " + super.getDescription() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
