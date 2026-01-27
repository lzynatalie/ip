public class UnknownCommand extends Command {

    public UnknownCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException();
    }
}
