package duke.component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.DukeInvalidEncodedTaskException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * A class that handles read and write operations to a destination storage file for the Duke application.
 */
public class Storage {
    private static final SimpleDateFormat DATE_PARSER = new SimpleDateFormat("dd/MM/yyyy HHmm");

    private String filePath;
    private Scanner fileReader;

    /**
     * Constructs a <code>Storage</code> object that handles I/O (read-writes) of a <code>TaskList</code> to a file.
     * 
     * @param filePath <code>String</code> containing the relative file path of the file to read/write to.
     * @throws IOException if an error occurred during a filesystem or file I/O operation.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        File file = new File(filePath);

        // No file matching this path: first construct any missing folders in the file path
        // Then create the file
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        assert file.exists() : "Attempting to proceed despite failure to create File!";

        // File exists: bind a Scanner to read input directly from the file when needed
        this.fileReader = new Scanner(file);
    }

    private static ArrayList<String> reconstructTags(String tagString) {
        if (tagString.equals("")) {
            return new ArrayList<String>();
        }

        ArrayList<String> tags = new ArrayList<String>();
        Collections.addAll(tags, tagString.replace("#", "").split(" "));
        return tags;
    }

    private static Task parseEncodedTask(String encodedString) throws DukeException {
        // Escape the pipe character as it is a metacharacter in regex
        String[] tokens = encodedString.split(" \\| ");

        Task task;
        switch (tokens[0]) {
        case "T":
            if (tokens.length != 4) {
                throw new DukeInvalidEncodedTaskException(4, "TodoTask", tokens.length, encodedString);
            }
            task = new TodoTask(tokens[3], Storage.reconstructTags(tokens[2]));
            break;
        case "D":
            if (tokens.length != 5) {
                throw new DukeInvalidEncodedTaskException(5, "DeadlineTask", tokens.length, encodedString);
            }
            try {
                Date eventTime = Storage.DATE_PARSER.parse(tokens[4]);
                task = new DeadlineTask(tokens[3], eventTime, Storage.reconstructTags(tokens[2]));
            } catch (ParseException e) {
                throw new DukeInvalidEncodedTaskException(5, "DeadlineTask", 4, encodedString);
            }
            break;
        case "E":
            if (tokens.length != 5) {
                throw new DukeInvalidEncodedTaskException(5, "EventTask", tokens.length, encodedString);
            }
            try {
                Date eventTime = Storage.DATE_PARSER.parse(tokens[4]);
                task = new EventTask(tokens[3], eventTime, Storage.reconstructTags(tokens[2]));
            } catch (ParseException e) {
                throw new DukeInvalidEncodedTaskException(5, "EventTask", 4, encodedString);
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

    /**
     * Reads from a file containing <code>TaskList</code> data, returning a new <code>TaskList</code> object if
     * successful.
     * 
     * @return a new <code>TaskList</code> object with the parsed data from the file.
     * @throws DukeException if an error occurred when reading from the file or data was unable to be parsed.
     */
    public TaskList readTaskList() throws DukeException {
        TaskList tasks = new TaskList();
        while (fileReader.hasNextLine()) {
            tasks.addTask(Storage.parseEncodedTask(fileReader.nextLine()));
        }
        return tasks;
    }

    /**
     * Writes to a file all data of a given <code>TaskList</code> object in an encoded format.
     * 
     * @param tasks the <code>TaskList</code> object whose data should be persisted to a file.
     * @throws IOException if an error occurred when writing to the file.
     */
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