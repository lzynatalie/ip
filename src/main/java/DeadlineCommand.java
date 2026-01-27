public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IrisException {
        if (description.isEmpty()) {
            throw new InvalidInputException("The description of a DEADLINE cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new InvalidInputException("The deadline of a DEADLINE cannot be empty.");
        }

        DeadlineTask task = new DeadlineTask(description, StringFormatter.toLocalDateTime(by));
        taskList.addTask(task);
        storage.store(taskList.asList());

        ui.showMessage("Got it. I've added this task:\n   " + task + "\n" + taskList.toTaskCountFormat());
    }
}
