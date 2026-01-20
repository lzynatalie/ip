public class TaskList {
    private String[] tasks = new String[100];
    private int numTasks = 0;

    public TaskList() {}

    public void addTask(String task) {
        tasks[numTasks] = task;
        numTasks++;
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < numTasks; i++) {
            string = string.concat((i + 1) + ". " + tasks[i] + "\n");
        }
        string = string.strip();
        return string;
    }
}
