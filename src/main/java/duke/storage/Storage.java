package duke.storage;

import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskFactory;
import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import static duke.task.TaskType.TODO;
import static duke.task.TaskType.EVENT;
import static duke.task.TaskType.DEADLINE;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

/**
 * Stores and retrieves persisting task information from the hard disk.
 */
public class Storage {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Path path;

    /**
     * Loads the Path based on given uri.
     *
     * @param uri the uri to create the Path object with.
     */
    public Storage(String uri) {
        this.path = Paths.get(uri);
    }

    /**
     * Reads task data from the hard disk.
     *
     * @return the task list containing all tasks read from disk.
     * @throws DukeIoException if some error occurs while reading the tasks.
     */
    public TaskList readFromDisk() throws DukeIoException {
        Charset charset = Charset.forName("ISO-8859-1");
        TaskList taskList = new TaskList();
        try {
            BufferedReader reader = Files.newBufferedReader(path, charset);
            String line = null;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] args = line.split(" \\| ");
                String code = args[0];
                boolean isDone = args[1].equals("1");
                String[] taskArgs = Arrays.copyOfRange(args, 2, args.length);
                if (code.equals("T")) {
                    taskList.add(TaskFactory.getTask(TODO, taskArgs, isDone));
                } else if (code.equals("D")) {
                    taskList.add(TaskFactory.getTask(DEADLINE, taskArgs, isDone));
                } else if (code.equals("E")) {
                    taskList.add(TaskFactory.getTask(EVENT, taskArgs, isDone));
                } else {
                    throw new IOException("Attempting to read unknown task from disk!");
                }
            }
            logger.log(INFO, "Read {0} tasks from disk successfully", new Object[]{taskList.count()});
        } catch (IOException e) {
            logger.log(WARNING, "Unable to read from disk. Will create new data file.");
            throw new DukeIoException(e.getMessage());
        }
        return taskList;
    }

    /**
     * Writes task data to the hard disk.
     *
     * @param taskList the task list containing all tasks to write to disk.
     * @return the task list written to disk.
     * @throws DukeException if some error occurs while writing the tasks.
     */
    public TaskList writeToDisk(TaskList taskList) throws DukeException {
        Charset charset = Charset.forName("ISO-8859-1");
        ArrayList<Task> tasks = taskList.getTaskList();
        try {
            // clear entire file and write on an empty file
            BufferedWriter bw = Files.newBufferedWriter(path, charset, CREATE, TRUNCATE_EXISTING, WRITE);
            for (Task task: tasks) {
                if (task != null) { // account for the first null placeholder
                    String desc = task.getDescription();
                    int isDone = task.getIsDone() ? 1 : 0;
                    if (task instanceof Todo) {
                        bw.write(String.format("T | %d | %s", isDone, desc));
                        bw.newLine();
                    } else if (task instanceof Deadline) {
                        String deadline = ((Deadline) task).getDeadline();
                        bw.write(String.format("D | %d | %s | /by | %s", isDone, desc, deadline));
                        bw.newLine();
                    } else if (task instanceof Event) {
                        String period = ((Event) task).getPeriod();
                        bw.write(String.format("E | %d | %s | /at | %s", isDone, desc, period));
                        bw.newLine();
                    } else {
                        throw new IOException("Attempting to write unknown task to disk!");
                    }
                }
            }
            bw.close();
            logger.log(INFO, "Write {0} tasks to disk successfully", new Object[]{taskList.count()});
        } catch (IOException e) {
            logger.log(SEVERE, "Unable to write to disk");
            throw new DukeIoException(e.getMessage());
        }
        return taskList;
    }
}
