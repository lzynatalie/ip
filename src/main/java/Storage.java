import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    public List<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (createFile()) {
            return tasks;
        }

        Scanner fileReader = new Scanner(filePath);
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

    public void store(List<Task> tasks) throws IOException {
        String saveContent = "";
        for (Task task : tasks) {
            saveContent = saveContent.concat(task.toSaveDataFormat() + "\n");
        }
        Files.writeString(filePath, saveContent);
    }

    public boolean createFile() throws IOException {
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
