package duke.storage;

import duke.dukeexception.DukeException;
import duke.task.Todo;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import static duke.dukeexception.DukeException.*;

/**
 * Represents the internal storage of the computer that the User is loading from
 * and writing into.
 */
public class Storage {
    /** File path associated with this Storage. */
    private String filePath;
    private boolean storageIsChanged = false;

    /**
     * Class constructor that specifies the file path to load from and write into.
     *
     * @param filePath File Path associated with this Storage object.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Marks that there are changes to the file path, and needs over-writing.
     */
    public void setChangedTrue() {
        storageIsChanged = true;
    }

    /**
     * Returns a true or false indicating whether storage has been updated.
     *
     * @return Status of storage.
     */
    public boolean isUpdated() {
        return storageIsChanged;
    }

    /**
     * Loads the Task data from this Storage's file path into an ArrayList.
     *
     * @return ArrayList containing all Tasks from file path.
     * @throws DukeException If File is corrupted or in wrong format.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasksLoadedFromFilePath = new ArrayList<>();
            BufferedReader fileInputStream = new BufferedReader(new FileReader(filePath));
            String fileData = fileInputStream.readLine();

            while (fileData != null) {
                String taskType = getTaskType(fileData);

                switch (taskType) {
                case "T":
                    Todo newTodo = readAndGetTodo(fileData);
                    tasksLoadedFromFilePath.add(newTodo);
                    break;
                case "D":
                    Deadline newDeadline = readAndGetDeadline(fileData);
                    tasksLoadedFromFilePath.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = readAndGetEvent(fileData);
                    tasksLoadedFromFilePath.add(newEvent);
                    break;
                default:
                    break;
                }
                fileData = fileInputStream.readLine();
            }
            fileInputStream.close();
            return tasksLoadedFromFilePath;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new DukeException(LOADING_ERROR);
        }
    }

    /**
     * Writes the new tasklist data into this Storage's file path.
     *
     * @param tasklist List of Tasks to write into file path.
     * @throws DukeException If writing process fails
     */
    public void writeToDisk(TaskList tasklist) throws DukeException {

        try {
            assert this.isUpdated() : "Writing to file when Storage was not updated";
            final int IS_DONE = 1;
            final int NOT_DONE = 0;

            FileWriter fw = new FileWriter(filePath);
            String lineToWrite;
            for (int k = 0; k < tasklist.size(); k++) {
                Task currTask = tasklist.get(k);
                int doneStatus = currTask.getDoneStatus() ? IS_DONE : NOT_DONE;
                if (currTask instanceof Todo) {
                    lineToWrite = "T" + "|" + doneStatus + "|" + currTask.getDescription();
                } else if (currTask instanceof Deadline) {
                    Deadline currDeadline = (Deadline) currTask;
                    lineToWrite = "D" + "|" + doneStatus + "|" + currDeadline.getDescription() + "|"
                            + currDeadline.getDate().getDateString() + " "
                            + currDeadline.getTiming().getTimeString();
                } else {
                    Event currEvent = (Event) currTask;
                    lineToWrite = "E" + "|" + doneStatus + "|" + currEvent.getDescription() + "|"
                            + currEvent.getDate().getDateString() + " "
                            + currEvent.getTiming().getTimeString();
                }
                fw.write(lineToWrite + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            throw new DukeException(WRITING_ERROR);
        }
    }

    private Deadline readAndGetDeadline(String fileData) throws DukeException {
        String[] fileTokens = fileData.split("\\|");
        String[] dateTimeTokens = fileTokens[3].split(" ");
        String taskDate = dateTimeTokens[0];
        String taskTime = dateTimeTokens[1];
        String taskDesc = fileTokens[2];
        int doneFlag = Integer.parseInt(fileTokens[1]);
        return new Deadline(taskDesc, taskDate, taskTime, doneFlag);
    }

    private Todo readAndGetTodo(String fileData) {
        String[] fileTokens = fileData.split("\\|");
        int doneFlag = Integer.parseInt(fileTokens[1]);
        String taskDesc = fileTokens[2];
        return new Todo(taskDesc, doneFlag);

    }

    private Event readAndGetEvent(String fileData) throws DukeException {
        String[] fileTokens = fileData.split("\\|");
        String[] dateTimeTokens = fileTokens[3].split(" ");
        String taskDate = dateTimeTokens[0];
        String taskTime = dateTimeTokens[1];
        String taskDesc = fileTokens[2];
        int doneFlag = Integer.parseInt(fileTokens[1]);
        return new Event(taskDesc, taskDate, taskTime, doneFlag);
    }

    private String getTaskType(String fileData) {
        String[] fileTokens = fileData.split("\\|");
        return fileTokens[0];
    }
}
