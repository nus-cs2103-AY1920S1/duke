package duke.storage;

import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.EventWithEndDateTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TodoTask;
import duke.task.TodoWithDateRangeTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * A Storage for the Duke program that stores information in a text file.
 */
public class TextStorage implements Storage {
    protected String fileLocation;

    public TextStorage(String fileLocation) {
        assert (new File(fileLocation)).exists() : "File location does not exist";
        this.fileLocation = fileLocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(fileLocation);
            for (Task task : tasks) {
                fw.write(task.toStorageFormat() + '\n');
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Cannot store tasks");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskList load() throws DukeException {
        try {
            String content = Files.readString(Paths.get(fileLocation), StandardCharsets.US_ASCII);
            List<Task> tasks = new ArrayList<Task>();
            for (String taskStorageFormat : content.split("\n")) {
                String[] taskDetails = taskStorageFormat.split(" \\| ");
                Task task = null;
                switch (taskDetails[0]) {
                case "T":
                    assert taskDetails.length == 3 || taskDetails.length == 5 : "Error reading task details";
                    if (taskDetails.length == 3) {
                        task = new TodoTask(taskDetails[2]);
                    } else {
                        task = new TodoWithDateRangeTask(taskDetails[2], taskDetails[3], taskDetails[4]);
                    }
                    break;
                case "D":
                    assert taskDetails.length == 4 : "Error reading task details";
                    task = new DeadlineTask(taskDetails[2], taskDetails[3]);
                    break;
                case "E":
                    assert taskDetails.length == 4 || taskDetails.length == 5 : "Error reading task details";
                    if (taskDetails.length == 4) {
                        task = new EventTask(taskDetails[2], taskDetails[3]);
                    } else if (taskDetails.length == 5) {
                        task = new EventWithEndDateTask(taskDetails[2], taskDetails[3], taskDetails[4]);
                    }
                    break;
                default:
                    throw new IOException();
                }
                assert taskDetails[1].equals("1") || taskDetails[1].equals("0") : "Error reading task details";
                if (taskDetails[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            return new TaskList(tasks);
        } catch (IOException e) {
            throw new DukeException("Unable to load file");
        }
    }
}
