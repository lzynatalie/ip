public class TaskList {
    private Task[] tasks = new Task[100];
    private int numTasks = 0;

    public TaskList() {}

    public Task addTask(TaskType taskType, String taskDescription) {
        Task task = new Task();
        switch (taskType) {
        case TODO:
            task = new ToDoTask(taskDescription);
            break;
        case DEADLINE:
            String[] deadlineParts = taskDescription.split("/");
            taskDescription = deadlineParts[0].strip();
            String by = deadlineParts[1].substring(3);
            task = new DeadlineTask(taskDescription, by);
            break;
        case EVENT:
            String[] eventParts = taskDescription.split("/");
            taskDescription = eventParts[0].strip();
            String from = eventParts[1].substring(5).strip();
            String to = eventParts[2].substring(3).strip();
            task = new EventTask(taskDescription, from, to);
            break;
        default:
            break;
        }
        tasks[numTasks] = task;
        numTasks++;
        return task;
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

    public int getNumTasks() {
        return numTasks;
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
