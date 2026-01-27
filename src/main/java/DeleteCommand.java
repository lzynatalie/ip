public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IrisException {
        if (taskList.getNumTasks() == 0) {
            throw new InvalidCommandException("There are no tasks right now.");
        }

        Task task = taskList.deleteTask(index);
        storage.store(taskList.asList());

        ui.showMessage("Noted. I've removed this task:\n   " + task + "\n" + taskList.toTaskCountFormat());
    }
}
