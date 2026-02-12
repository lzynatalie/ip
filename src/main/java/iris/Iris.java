package iris;

import iris.command.Command;
import iris.exception.IrisException;

/**
 * Represents the program logic.
 */
public class Iris {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises an Iris object using the given file path.
     *
     * @param filePath File path of saved tasks.
     */
    public Iris(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IrisException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Iris program.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (IrisException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showHorizontalLine();
            }
        }
    }

    public static void main(String[] args) {
        new Iris("./data/iris.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, storage);
        } catch (IrisException e) {
            return e.getMessage();
        }
    }
}
