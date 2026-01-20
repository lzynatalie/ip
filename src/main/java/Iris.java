import java.util.Scanner;

public class Iris {
    public static final String CHATBOT_NAME = "Iris";
    public static final String HORIZONTAL_LINE = "⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻";

    public static void main(String[] args) {
        String welcomeMessage = HORIZONTAL_LINE + "\n"
                + "Hello! I'm " + CHATBOT_NAME + ".\n"
                + "What can I do for you?\n"
                + HORIZONTAL_LINE;
        String goodbyeMessage = "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE;
        Scanner scanner = new Scanner(System.in);

        System.out.println(welcomeMessage);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput + "\n" + HORIZONTAL_LINE);
            userInput = scanner.nextLine();
        }
        System.out.println(goodbyeMessage);
    }
}
