import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Storage {
    private final static String FILENAME = "duke.txt";
    private static Path DATA_FOLDER = Path.of(".", "data");

    private Path storageFilePath;

    private Storage() {
        this.storageFilePath = Path.of(DATA_FOLDER.toString(), FILENAME);
    }

    public static Storage initialize() {
        Storage storage = new Storage();
        try {
            if (!Files.exists(DATA_FOLDER)) {
                Files.createDirectories(DATA_FOLDER);
            }

            if (!Files.exists(storage.storageFilePath)) {
                Files.createFile(storage.storageFilePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return storage;
    }

    public void saveTasks(List<Task> taskList) {
        List<String> entries = taskList.stream().map(this::toFileEntry).collect(Collectors.toList());
        try {
            Files.write(this.storageFilePath, entries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> loadTasks() {
        try {
            List<String> entries = Files.readAllLines(this.storageFilePath);
            return entries.stream().map(this::parse).flatMap(Optional::stream).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private String toFileEntry(Task input) {
        if (input instanceof Todo) {
            return String.format("%s | %d | %s",
                    "T",
                    input.isCompleted ? 1 : 0,
                    input.getTaskName());
        } else if (input instanceof Deadline) {
            return String.format("%s | %d | %s | %s",
                    "D",
                    input.isCompleted ? 1 : 0,
                    input.getTaskName(),
                    ((Deadline) input).getBy());
        } else if (input instanceof Event) {
            return String.format("%s | %d | %s | %s",
                    "E",
                    input.isCompleted ? 1 : 0,
                    input.getTaskName(),
                    ((Event) input).getAt());
        }
        return "";
    }

    private Optional<Task> parse(String line) {
        try {
            List<String> data = Stream.of(line.split("\\|")).map(String::trim).collect(Collectors.toList());
            Task task;
            switch (data.get(0)) {
            case "T":
                task = new Todo(data.get(2));
                break;
            case "D":
                task = new Deadline(data.get(2), data.get(3));
                break;
            case "E":
                task = new Event(data.get(2), data.get(3));
                break;
            default:
                throw new ParseException(data.get(0) + " is not an acceptable argument", 0);
            }
            task.setCompleted(data.get(1).equals("1"));
            return Optional.of(task);
        } catch (ParseException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


}
