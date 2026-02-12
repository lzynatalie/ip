package iris;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import iris.exception.InvalidInputException;
import iris.exception.IrisException;
import iris.task.DeadlineTask;
import iris.task.EventTask;
import iris.task.Task;
import iris.task.ToDoTask;

/**
 * An object used to read and write tasks to a file.
 */
public class Storage {
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Loads tasks from hard disk.
     *
     * @return List of tasks.
     * @throws IrisException If an error occurred.
     */
    public List<Task> load() throws IrisException {
        ArrayList<Task> tasks = new ArrayList<>();

        boolean fileCreated;
        try {
            fileCreated = createFile();
        } catch (IOException e) {
            throw new IrisException("Oops, failed to load tasks.");
        }

        if (fileCreated) {
            return tasks;
        }

        Scanner fileReader;
        try {
            fileReader = new Scanner(filePath);
        } catch (IOException e) {
            throw new IrisException("Oops, failed to load tasks.");
        }

        while (fileReader.hasNext()) {
            String task = fileReader.nextLine();
            String[] taskParts = task.split(" \\| ");
            boolean isDone = taskParts[1].equals("1");
            switch (taskParts[0]) {
                case "T" -> loadToDoTask(tasks, taskParts[2], isDone);
                case "D" -> loadDeadlineTask(tasks, taskParts[2], isDone, taskParts[3]);
                case "E" -> loadEventTask(tasks, taskParts[2], isDone, taskParts[3], taskParts[4]);
                default -> throw new IrisException("Unknown task type");
            }
        }

        return tasks;
    }

    /**
     * Stores tasks onto hard disk.
     *
     * @param tasks List of tasks.
     * @throws IrisException If an error occurred.
     */
    public void store(List<Task> tasks) throws IrisException {
        String saveContent = "";
        for (Task task : tasks) {
            saveContent = saveContent.concat(task.toSaveDataFormat() + "\n");
        }
        try {
            Files.writeString(filePath, saveContent);
        } catch (IOException e) {
            throw new IrisException("Oops, failed to save tasks.");
        }
    }

    private boolean createFile() throws IOException {
        Path directoryPath = filePath.getParent();
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(filePath.getParent());
        }

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            return true;
        }
        return false;
    }

    private void loadToDoTask(ArrayList<Task> tasks, String description, boolean isDone) {
        ToDoTask task = new ToDoTask(description, isDone);
        tasks.add(task);
    }

    private void loadDeadlineTask(ArrayList<Task> tasks, String description, boolean isDone, String by)
            throws InvalidInputException {
        DeadlineTask task = new DeadlineTask(description, isDone, StringFormatter.toLocalDateTime(by));
        tasks.add(task);
    }

    private void loadEventTask(ArrayList<Task> tasks, String description, boolean isDone, String from, String to)
            throws InvalidInputException {
        EventTask task = new EventTask(description, isDone,
                StringFormatter.toLocalDateTime(from), StringFormatter.toLocalDateTime(to));
        tasks.add(task);
    }
}
