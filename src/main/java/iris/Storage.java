package iris;

import iris.exception.IrisException;
import iris.task.DeadlineTask;
import iris.task.EventTask;
import iris.task.Task;
import iris.task.ToDoTask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
     * @throws IrisException
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
            case "T" -> tasks.add(new ToDoTask(taskParts[2], isDone));
            case "D" -> tasks.add(new DeadlineTask(taskParts[2], isDone,
                    StringFormatter.toLocalDateTime(taskParts[3])));
            case "E" -> tasks.add(new EventTask(taskParts[2], isDone,
                    StringFormatter.toLocalDateTime(taskParts[3]),
                    StringFormatter.toLocalDateTime(taskParts[4])));
            }
        }

        return tasks;
    }

    /**
     * Stores tasks onto hard disk.
     *
     * @param tasks List of tasks.
     * @throws IrisException
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
}
