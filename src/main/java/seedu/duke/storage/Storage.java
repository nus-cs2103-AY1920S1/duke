package seedu.duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.commons.exceptions.loadexceptions.LoadException;
import seedu.duke.commons.exceptions.storageexceptions.SavingUnsuccessfulException;
import seedu.duke.logic.parser.Parser;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Defines a Storage object.
 * Contains a file path and TaskList stored in the file that the file path points to.
 */
public class Storage {

    private String filePath;
    private String dirPath;
    private TaskList taskList;
    private static final char DIRSEPARATOR = System.getProperty("os.name").startsWith("Windows")
            ? '\\'
            : '/';


    /**
     * Creates a Storage object given a file path.
     * Stores the file path as an attribute of this particular instance of the Storage class.
     * @param filePath String representation of file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.dirPath = getDirAsString(filePath);
    }

    /**
     * Returns a String representation of the directory the file at filePath is in.
     * @param filePath Path of file to get directory of.
     * @return String representation of the directory the file at filePath is in.
     */
    public static String getDirAsString(String filePath) {
        int index = 0;
        for (int i = filePath.length() - 2; i >= 0; i--) {
            if (filePath.charAt(i) == DIRSEPARATOR) {
                index = i;
                break;
            }
        }
        String res = filePath.substring(0, index);

        assert res != null && !res.equals("") : "Directory returned by filepath is empty.";
        return res;
    }

    /**
     * Returns a new Storage object that contains TaskList of Tasks parsed from the file path of this storage.
     * @return Storage object containing TaskList of Tasks parsed from this storage's file path attribute.
     * @throws DukeException If reading an existing file or creating a new file is unsuccessful.
     */
    public Storage load() throws DukeException {
        try {
            File dir = new File(dirPath);
            dir.mkdir();

            File file = new File(filePath);

            if (file.createNewFile()) {
                this.taskList = new TaskList();
            } else {
                this.taskList = Parser.parse(this);
            }
            assert this.taskList != null : "Storage load method did not create any TaskList";
            return this;
        } catch (IOException | DukeException ex) {
            ex.printStackTrace();
            throw new LoadException("Error loading specified file.");
        }
    }

    /**
     * Saves given TaskList to the file located at this Storage's file path.
     * @param tasks TaskList of Tasks, presumably stored in current memory.
     * @param ui Ui instance used by a client that calls this method.
     * @throws DukeException If writing to the original file at this Storage's file path is unsuccessful.
     */
    public void save(TaskList tasks, Ui ui) throws DukeException {
        try {
            File dir = new File(dirPath);
            boolean directoryIsCreated = dir.mkdir();

            File file = new File(filePath);
            boolean fileIsCreated = file.createNewFile();
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                char type = task.getType().toString().charAt(0);
                char isDone = task.getIsDone() == true ? '1' : '0';
                String taskString = task.getTaskString();
                DateAndTime dateAndTime = task.getDateAndTime();
                String out = "";

                if (task.getDateAndTime() != null) {
                    out = type + " | " + isDone + " | " + taskString + " | " + dateAndTime + "\n";
                } else {
                    out = type + " | " + isDone + " | " + taskString + "\n";
                }

                fileWriter.append(out);
            }
            fileWriter.close();

            if (directoryIsCreated) {
                ui.showNotification("Original storage directory no longer exists!\n"
                        + "BUT don't worry! We have restored a new one in it's place in the same location and "
                        + "performed the required operation. :)");
            } else if (fileIsCreated) {
                ui.showNotification("Original storage file no longer exists!\n"
                        + "BUT don't worry! We have restored a new one in it's place in the same location and "
                        + "performed the required operation. :)");
            }
        } catch (Exception ex) {
            throw new SavingUnsuccessfulException("Critical Error: Saving Unsuccessful.");
        }
    }

    /**
     * Returns the file path of this Storage instance as a String.
     * @return This Storage's filePath attribute.
     */
    public String getFilePath() {
        assert filePath != null && !filePath.equals("") : "File path returned by Storage object is empty.";
        return filePath;
    }

    /**
     * Returns the TaskList stored in this instance of Storage.
     * @return This Storage's taskList attribute.
     */
    public TaskList getTaskList() {
        assert taskList != null : "TaskList returned by Storage object is empty.";
        return taskList;
    }
}
