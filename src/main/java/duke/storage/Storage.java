package duke.storage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Class for reading and writing Duke data.
 */
public class Storage {
    /**
     * Charset for reading and writing .txt files.
     */
    final static Charset ENCODING = StandardCharsets.UTF_8;

    private Path path;

    /**
     * Constructor for Storage.
     *
     * @param path path for duke.txt
     * @throws IOException
     */
    public Storage(String path) throws IOException {
        this.path = Paths.get(path);
    }

    /**
     * Reads text file from path.
     *
     * @return text lines from .txt as List of Strings
     * @throws IOException
     */
    private List<String> read() throws IOException {
        return Files.readAllLines(path, ENCODING);
    }

    /**
     * Writes text file to path.
     *
     * @param lines text lines to write onto .txt as List of Strings
     * @throws IOException
     */
    private void write(List<String> lines) throws IOException {
        Files.write(path, lines, ENCODING);
    }

    /**
     * Reads text file and formatted into ArrayList of tasks to be used on other classes.
     *
     * @return ArrayList of tasks
     * @throws IOException
     */
    public ArrayList<Task> readDuke() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        List<String> lines = read();
        for (String s : lines) {
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

    /**
     * Writes text file in .txt format.
     *
     * @param tasks List of tasks
     * @throws IOException
     */
    public void writeDuke(List<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<String>();
        for (Task task : tasks) {
            lines.add(task.toFile());
        }
        write(lines);
    }
}
