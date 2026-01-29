package iris;

import java.util.Scanner;

/**
 * An object used to interact with the user.
 * <br>
 * This class provides methods to read user input and display program outputs.
 */
public class Ui {
    public static final String CHATBOT_NAME = "Iris";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        showHorizontalLine();
        System.out.printf("Hello! I'm %s.\nWhat can I do for you?\n", CHATBOT_NAME);
        showHorizontalLine();
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showHorizontalLine() {
        for (int i = 0; i < 80; i++) {
            System.out.print("⸻");
        }
        System.out.println();
    }
}
