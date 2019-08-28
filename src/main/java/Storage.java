import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    final static Charset ENCODING = StandardCharsets.UTF_8;
    private Path path;

    public Storage(String path) throws IOException {
        this.path = Paths.get(path);
    }

    private List<String> read() throws IOException {
        return Files.readAllLines(path, ENCODING);
    }

    private void write(List<String> lines) throws IOException {
        Files.write(path, lines, ENCODING);
    }

    public ArrayList<Task> readDuke() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        List<String> lines = read();
        for(String s : lines) {
            String[] split = s.split("\\|");
            Task task = new Task();
            if (split[0].contains("T")) {
                task = new ToDo(split[2]);
            } else if (split[0].contains("D")) {
                task = new Deadline(split[2], split[3]);
            } else if (split[0].contains("E")) {
                task = new Event(split[2], split[3]);
            }
            if (split[1].contains("Y")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return tasks;
    }

    public void writeDuke(List<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<String>();
        for (Task task : tasks) {
            lines.add(task.toFile());
        }
        write(lines);
    }
}
