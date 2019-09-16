package duke;

import duke.calendar.Date;
import duke.calendar.Time;
import duke.exception.DukeException;
import duke.exception.StorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage in the hard disk.
 * A <code>Storage</code> object corresponds to a specific file path that dictates the file that information about the
 * tasks in the <code>TaskList</code> should be loaded from and written to every time a change occurs.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructor for <code>Storage</code>.
     * @param filePath Path to file that should be written to and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file given by the file path.
     * Stores and returns the tasks within an <code>ArrayList</code>.
     * @return An <code>ArrayList</code> containing every <code>Task</code> written in the file.
     * @throws DukeException If the file path is invalid or there are problems with the data in the file.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(filePath);
            file.createNewFile();
            Scanner scanner = new Scanner(file);
            return getTasks(scanner);
        } catch (FileNotFoundException exception) {
            throw new StorageException("OOPS!!! Please specify a valid file path.");
        } catch (IOException exception) {
            throw new StorageException("OOPS!!! Something went wrong: " + exception.getMessage());
        }
    }

    private ArrayList<Task> getTasks(Scanner scanner) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (scanner.hasNextLine()) {
            String taskLine = scanner.nextLine();

            String[] taskDetails = taskLine.split(" \\| ");
            String taskType = getTaskDetail(taskDetails, 0);
            String taskDoneStatus = getTaskDetail(taskDetails, 1);
            String taskDescription = getTaskDetail(taskDetails, 2);

            Task task = getTask(taskDetails, taskType, taskDescription);
            assert (task != null) : "Data in file should be stored in the correct format "
                            + "and should not cause exceptions.";
            setTaskDoneStatus(taskDoneStatus, task);
            tasks.add(task);
        }
        return tasks;
    }

    private String getTaskDetail(String[] taskDetails, int i) {
        if (taskDetails.length <= i) {
            return null;
        }
        return taskDetails[i].trim();
    }

    private Task getTask(String[] taskDetails, String taskType, String taskDescription) throws DukeException {
        if (taskType.equals("T")) {
            return getTodoTask(taskDescription);
        } else if (taskType.equals("D")) {
            return getDeadlineTask(taskDetails, taskDescription);
        } else {
            return getEventTask(taskDetails, taskDescription);
        }
    }

    private Task getEventTask(String[] taskDetails, String taskDescription) throws DukeException {
        String taskStartDate = getTaskDetail(taskDetails, 3);
        String taskStartTime = getTaskDetail(taskDetails, 4);
        String taskEndDate = getTaskDetail(taskDetails, 5);
        String taskEndTime = getTaskDetail(taskDetails, 6);
        return new Event(taskDescription, new Date(taskStartDate), new Time(taskStartTime),
                new Date(taskEndDate), new Time(taskEndTime));
    }

    private Todo getTodoTask(String taskDescription) {
        return new Todo(taskDescription);
    }

    private Task getDeadlineTask(String[] taskDetails, String taskDescription) throws DukeException {
        String taskDate = getTaskDetail(taskDetails, 3);
        String taskTime = getTaskDetail(taskDetails, 4);
        return new Deadline(taskDescription, new Date(taskDate), new Time(taskTime));
    }

    /**
     * Writes the tasks in the <code>TaskList</code> to the file given by the file path.
     * @param taskList <code>TaskList</code> containing all the tasks.
     * @throws StorageException If the file cannot be written to.
     */
    public void writeToFile(TaskList taskList) throws StorageException {
        try {
            FileWriter writer = new FileWriter(filePath);
            ArrayList<Task> tasks = taskList.getList();
            for (Task task : tasks) {
                writeTask(writer, task);
            }
            writer.close();
        } catch (IOException exception) {
            throw new StorageException("OOPS!!! Something went wrong: " + exception.getMessage());
        }
    }

    private void writeTask(FileWriter writer, Task task) throws IOException {
        StringBuilder builder = new StringBuilder();
        appendTaskType(task, builder);
        appendTaskStatus(task, builder);
        appendTaskDescription(task, builder);
        appendTaskDetails(task, builder);
        writer.write(builder.toString() + "\n");
    }

    private void appendTaskType(Task task, StringBuilder builder) {
        builder.append(task.getType() + " | ");
    }

    private void appendTaskStatus(Task task, StringBuilder builder) {
        if (task.isDone()) {
            builder.append("1 | ");
        } else {
            builder.append("0 | ");
        }
    }

    private void appendTaskDescription(Task task, StringBuilder builder) {
        builder.append(task.getDescription());
    }

    private void appendTaskDetails(Task task, StringBuilder builder) {
        if (task.getType().equals("D")) {
            appendDeadlineDetails((Deadline) task, builder);
        } else if (task.getType().equals("E")) {
            appendEventDetails((Event) task, builder);
        }
    }

    private void appendDeadlineDetails(Deadline deadline, StringBuilder builder) {
        String rawDate = deadline.getDate().getRawDate();
        builder.append(" | " + rawDate);
        String rawTime = deadline.getTime().getRawTime();
        if (rawTime != null) {
            builder.append(" | " + rawTime);
        }
    }

    private void appendEventDetails(Event event, StringBuilder builder) {
        String rawStartDate = event.getStartDate().getRawDate();
        builder.append(" | " + rawStartDate);

        String rawStartTime = event.getStartTime().getRawTime();
        if (rawStartTime != null) {
            builder.append(" | " + rawStartTime);
        }
        builder.append(" | ");

        String rawEndDate = event.getEndDate().getRawDate();
        if (rawEndDate != null) {
            builder.append(rawEndDate);
        }

        String rawEndTime = event.getEndTime().getRawTime();
        if (rawEndTime != null) {
            if (rawEndDate != null) {
                builder.append(" | ");
            }
            builder.append(" " + rawEndTime);
        }
    }

    private void setTaskDoneStatus(String taskDoneStatus, Task task) {
        if (taskDoneStatus.equals("1")) {
            task.markAsDone();
        }
    }
}
