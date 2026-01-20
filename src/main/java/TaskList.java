public class TaskList {
    private Task[] tasks = new Task[100];
    private int numTasks = 0;

    public TaskList() {}

    public void addTask(String taskDescription) {
        Task task = new Task(taskDescription);
        tasks[numTasks] = task;
        numTasks++;
    }

    public void markTask(int index) {
        tasks[index - 1].markAsDone();
    }

    public void unmarkTask(int index) {
        tasks[index - 1].markAsUndone();
    }

    public Task getTask(int index) {
        return tasks[index - 1];
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
