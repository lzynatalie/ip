package iris.task;

/**
 * Represents a task to be done.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Initialises a task.
     *
     * @param description Description of this task.
     * @param isDone Completion status of this task.
     */
    public Task(String description, boolean isDone) {
        assert !description.isEmpty() : "Description should not be empty";
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this(description, false);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public abstract String toSaveDataFormat();

    @Override
    public String toString() {
        String statusIcon = isDone ? "[X]" : "[ ]";
        return statusIcon + " " + description;
    }
}
