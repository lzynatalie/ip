public class ToDoTask extends Task {

    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toSaveDataFormat() {
        String status = super.isDone() ? "1" : "0";
        return "T | " + status + " | " + super.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
