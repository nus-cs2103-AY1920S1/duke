import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Storage {
    public static final SimpleDateFormat DATE_PARSER = new SimpleDateFormat("dd/MM/yyyy HHmm");

    private String filePath;
    private Scanner fileReader;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        File file = new File(filePath);

        // No file matching this path: first construct any missing folders in the file path
        // Then create the file
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        // File exists: bind a Scanner to read input directly from the file when needed
        this.fileReader = new Scanner(file);
    }

    public static Task parseEncodedTask(String encodedString) throws DukeException {
        // Escape the pipe character as it is a metacharacter in regex
        String[] tokens = encodedString.split(" \\| ");

        Task task;
        switch (tokens[0]) {
        case "T":
            if (tokens.length != 3) {
                throw new DukeInvalidEncodedTaskException(3, "TodoTask", tokens.length, encodedString);
            }
            task = new TodoTask(tokens[2]);
            break;
        case "D":
            if (tokens.length != 4) {
                throw new DukeInvalidEncodedTaskException(4, "DeadlineTask", tokens.length, encodedString);
            }
            try {
                Date eventTime = Storage.DATE_PARSER.parse(tokens[3]);
                task = new DeadlineTask(tokens[2], eventTime);
            } catch (ParseException e) {
                throw new DukeInvalidEncodedTaskException(4, "DeadlineTask", 4, encodedString);
            }
            break;
        case "E":
            if (tokens.length != 4) {
                throw new DukeInvalidEncodedTaskException(4, "EventTask", tokens.length, encodedString);
            }
            try {
                Date eventTime = Storage.DATE_PARSER.parse(tokens[3]);
                task = new EventTask(tokens[2], eventTime);
            } catch (ParseException e) {
                throw new DukeInvalidEncodedTaskException(4, "EventTask", 4, encodedString);
            }
            break;
        default:
            throw new DukeException("Encoded string corresponds to an unrecognised task type", encodedString);
        }

        if (tokens[1].equals("1")) {
            task.complete();
        }
        return task;
    }

    // Reads all encoded tasks from the file specified by filePath and returns the TaskList
    public TaskList readTaskList() throws DukeException {
        TaskList tasks = new TaskList();
        while (fileReader.hasNextLine()) {
            tasks.addTask(Storage.parseEncodedTask(fileReader.nextLine()));
        }
        return tasks;
    }

    // Writes all tasks in the TaskList in an encoded format to the file specified by filePath 
    public void writeTaskList(TaskList tasks) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        int numTasks = tasks.numberOfTasks();
        for (int i = 1; i <= numTasks; i++) {
            bw.write(tasks.getTask(i).toEncodedString());
            bw.write("\n");
        }
        // Flush and close the buffered writer
        bw.close();
    }

    @Override
    public String toString() {
        String desc = "This Storage instance reads and writes TaskList data from the file with path:";
        return String.format("%s\n%s", desc, this.filePath);
    }
}