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
        TaskList taskList = new TaskList();

        System.out.println(welcomeMessage);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                listTasks(taskList);
            } else if (userInput.contains("unmark")) {
                unmarkTask(taskList, userInput);
            } else if (userInput.contains("mark")) {
                markTask(taskList, userInput);
            } else {
                addTask(taskList, userInput);
            }
            System.out.println(HORIZONTAL_LINE);
            userInput = scanner.nextLine();
        }
        System.out.println(goodbyeMessage);
    }

    private static void listTasks(TaskList taskList) {
        System.out.println(taskList);
    }

    private static void addTask(TaskList taskList, String userInput) {
        taskList.addTask(userInput);
        System.out.println("added: " + userInput);
    }

    private static void markTask(TaskList taskList, String userInput) {
        String[] inputs = userInput.split(" ");
        int index = Integer.parseInt(inputs[1]);
        Task task = taskList.getTask(index);
        taskList.markTask(index);
        System.out.println("Nice! I've marked this task as done:\n   " + task);
    }

    private static void unmarkTask(TaskList taskList, String userInput) {
        String[] inputs = userInput.split(" ");
        int index = Integer.parseInt(inputs[1]);
        Task task = taskList.getTask(index);
        taskList.unmarkTask(index);
        System.out.println("OK, I've marked this task as not done yet:\n   " + task);
    }
}
