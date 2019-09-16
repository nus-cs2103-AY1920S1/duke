package duke.storage;

import duke.command.DukeInvalidArgumentException;
import duke.ui.MainWindow;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.TodoTask;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Map;

/**
 * Abstraction of the file storage of tasks.
 * Saves the task data in a readable and editable json format.
 */
public class Storage {

    /** The default filename of the task data file to use. */
    private static final String DEFAULT_FILE_NAME = "taskData.txt";
    /** The upward recursive search limit for the existing specified dirName. */
    private static final int DIRECTORY_SEARCH_LIMIT = 5;

    /** The filename of the task data file to use. */
    private String dataFileName;
    /** The directory name to use for storing the task data file. */
    private String dirName;
    /** The absolute filePath of the task data file to use. */
    private String filePath;
    /** The user interface object for displaying error messages. */
    private MainWindow ui;

    /**
     * Primary constructor of the storage object.
     * Searches recursively upward from the present working directory for
     * the directory dirName.
     * If it is not found, the directory is creating in
     * the present working directory.
     * Uses DEFAULT_FILE_NAME for the file name.
     *
     * @param dirName The directory name as a string.
     * @param ui The user interface object to use.
     */
    public Storage(String dirName, MainWindow ui) {
        this.dirName = dirName;
        this.ui = ui;
        this.dataFileName = DEFAULT_FILE_NAME;
        setFilePath();
    }

    /**
     * Secondary constructor of the storage object.
     * Searches recursively upward from the present working directory for
     * the directory dirName.
     * If it is not found, the directory is creating in
     * the present working directory.
     * Uses dataFileName for the dataFileName of the file.
     *
     * @param dataFilename The file name as a string.
     * @param dirName The directory name as a string.
     * @param ui The user interface object to use.
     */
    public Storage(String dataFilename, String dirName, MainWindow ui) {
        this.dirName = dirName;
        this.ui = ui;
        this.dataFileName = dataFilename;
        setFilePath();
    }

    /**
     * Reads the task data file and loads the task data into the task list.
     * Re-validates the input during construction of the task instances,
     * skipping invalid inputs.
     * See getTaskType/DoneStatus/Description for the validation.
     * Also refer to the task constructors for additional info on validation.
     *
     * @param taskList The TaskList object to load the tasks into.
     */
    public void loadTasksToList(TaskList taskList) {
        Scanner dataScanner = getScanner();
        if (dataScanner == null) {
            return;
        }

        int lineNumber = 0;

        while (dataScanner.hasNextLine()) {
            lineNumber++;
            parseAndStoreTask(taskList, dataScanner.nextLine(), lineNumber);
        }
    }

    /**
     * Private method to parse an input string of task data and store it into the task list.
     * Also requires an input linenumber integer to print a more informative
     * storage file error message with a line number indication.
     *
     * @param taskList The task list to store the parsed task in.
     * @param input The input string of task data to parse.
     * @param lineNumber The integer line number for error indication.
     */
    private void parseAndStoreTask(TaskList taskList, String input, int lineNumber) {
        try {
            Map<StorageKey, String> inputs = StorageParser.parseJsonLine(input);

            TaskType taskType = getTaskType(inputs.get(StorageKey.TYPE));
            boolean isTaskDone = getDoneStatus(inputs.get(StorageKey.DONE));
            String taskDescription = getDescription(inputs.get(StorageKey.DESCRIPTION));
            String taskTiming = inputs.get(StorageKey.TIME);

            // Create appropriate task from parsed taskType
            Task taskToAdd = createTask(taskType, taskDescription, taskTiming, isTaskDone);
            assert taskToAdd != null : "Tried to load invalid task to task list when parsing storage.";

            taskList.addTask(taskToAdd);
        } catch (DukeTaskFileParseException | DukeInvalidArgumentException ex) {
            ui.showMessage(ex.getDisplayMsg()
                    + String.format("\n   Error in storage file line number: %d", lineNumber));
        }
    }

    /**
     * Private method to create the appropriate task type from given TaskType.
     * Also sets the done status of the task accordingly.
     * Used in parseAndStoreTask method.
     *
     * @param taskType TaskType enum of the task type to create.
     * @param taskDescription String task description to create the task.
     * @param taskTiming String task timing to create the task.
     * @return Appropriate created task corresponding to the given TaskType enum.
     * @throws DukeInvalidArgumentException If validation of task information provided fails.
     * @throws DukeTaskFileParseException If TaskType enum provided is invalid.
     */
    private Task createTask(TaskType taskType, String taskDescription, String taskTiming, boolean isTaskDone)
            throws DukeInvalidArgumentException, DukeTaskFileParseException {
        Task taskToAdd;
        switch (taskType) {
        case TODO:
            taskToAdd = new TodoTask(taskDescription);
            break;
        case DEADLINE:
            taskToAdd = new DeadlineTask(taskDescription, taskTiming);
            break;
        case EVENT:
            taskToAdd = new EventTask(taskDescription, taskTiming);
            break;
        default:
            throw new DukeTaskFileParseException(
                    "Unhandled taskType encountered",
                    " =X  Oops! I am not trained to handle this type of Tasks...\n");
        }
        taskToAdd.setDone(isTaskDone);

        return taskToAdd;
    }

    /**
     * Retrieves a scanner using the file path of this storage instance.
     * If the file does not exist, a ui notification is shown to the user.
     *
     * @return The scanner object, or null if the file does not exist.
     */
    private Scanner getScanner() {
        try {
            return new Scanner(new File(filePath));
        } catch (FileNotFoundException ex) {
            ui.showMessage(" I did not find any previous data in your data directory\n"
                    + " I'll try to create one when you save something!");
            return null;
        }
    }

    /**
     * Writes the specified task list to disk.
     * It throws a file write exception if an IOException occurs.
     *
     * @param tasks The task list to save to disk.
     * @throws DukeFileWriteException If an IOException from FileWriter occurs.
     */
    public void saveTasksToDisk(TaskList tasks) throws DukeFileWriteException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (Task task : tasks.getAllTasks()) {
                String jsonLineStart = String.format(
                        "{ %s: %s, %s: %s, %s: %s",
                        StorageKey.TYPE.toString(), task.getTaskType().toString(),
                        StorageKey.DONE.toString(), ((Boolean) task.isDone()).toString(),
                        StorageKey.DESCRIPTION.toString(), task.getDescription());

                fileWriter.write(jsonLineStart);

                if (task.getTiming() != null) {
                    fileWriter.write(String.format(", %s: %s",
                            StorageKey.TIME.toString(),
                            task.getTiming()));
                }

                fileWriter.write(" }\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            throw new DukeFileWriteException(
                    ex.getMessage(),
                    " =X  OOPS!!! I failed to write the task data to disk!");
        }
    }

    /**
     * Private method to get the TaskType enum from a string.
     *
     * @param input The task type string.
     * @return The TaskType enum
     * @throws DukeTaskFileParseException If there is no valid TaskType for the input string.
     */
    private TaskType getTaskType(String input) throws DukeTaskFileParseException {
        try {
            return TaskType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new DukeTaskFileParseException(
                    "Invalid task type encountered while parsing task file",
                    " =X  Oops! I encountered an invalid task type value while\n"
                            + "   reading your file.");
        }
    }

    /**
     * Private method to get the boolean done status from a string.
     * Unlike Boolean.valueOf, only accepts 'true' or 'false' as valid arguments.
     *
     * @param status The status input string.
     * @return The parsed boolean value.
     * @throws DukeTaskFileParseException If status string is invalid as described.
     */
    private boolean getDoneStatus(String status) throws DukeTaskFileParseException {
        if (status != null) {
            if (status.equalsIgnoreCase("true")) {
                return true;
            } else if (status.equalsIgnoreCase("false")) {
                return false;
            }
        }

        throw new DukeTaskFileParseException(
                "Invalid done status number encountered while parsing task file",
                " =X  Oops! I encountered an invalid or missing done value\n"
                        + "   while reading your file.");
    }

    /**
     * Private method for validating the description of the task.
     * See TaskUtil for more information on validation of the description.
     * Also returns the description again.
     *
     * @param description The description input string.
     * @return The description string.
     * @throws DukeTaskFileParseException If the description is empty.
     */
    private String getDescription(String description) throws DukeTaskFileParseException {
        try {
            TaskUtil.validateTaskDescription(description);
            return description;
        } catch (DukeInvalidArgumentException | NullPointerException ex) {
            throw new DukeTaskFileParseException(
                    "Invalid task description encountered when parsing task file",
                    " =X  Oops! I encountered an empty description while\n"
                            + "   reading your file.");
        }
    }

    /**
     * Private method called in the constructor of the storage instance.
     * Using the provided dirName, tries to search for an absolute directory path
     * recursively upward up to the search limit.
     * If it is not found, the absolute directory path used is the present
     * working directory and the directory is created.
     * If directory creation fails, the user is alerted that the app cannot
     * save changes to disk.
     * The absolute file path is then determined using the directory path and
     * the DATA_FILE_NAME string constant.
     */
    private void setFilePath() {
        String workingDir = System.getProperty("user.dir");
        Path currentDir = Paths.get(workingDir);
        int recursiveSearchCount = 1;

        while (!Files.isDirectory(Paths.get(currentDir.toString(), dirName))
                && recursiveSearchCount <= DIRECTORY_SEARCH_LIMIT) {
            currentDir = currentDir.getParent();
            recursiveSearchCount++;
        }

        if (recursiveSearchCount > 5) {
            //create directory in pwd since it dosen't exist
            createFileDirectory();
        } else {
            this.filePath = Paths.get(currentDir.toString(), dirName, dataFileName).toString();
        }
    }

    /**
     * Creates a data directory in the working directory of the program.
     * Also sets the filePath associated with this storage instance.
     */
    private void createFileDirectory() {
        String workingDir = System.getProperty("user.dir");
        Path fallbackDirPath = Paths.get(workingDir, dirName);

        try {
            if (!Files.isDirectory(fallbackDirPath)) {
                Files.createDirectory(fallbackDirPath);
            }
        } catch (IOException ex) {
            printNoStorageMsg();
        }
        this.filePath = Paths.get(fallbackDirPath.toString(), dataFileName).toString();
    }

    /**
     * Private method to display the no save-to-disk function message
     * to the user.
     */
    private void printNoStorageMsg() {
        ui.showMessage(" =X  Oops! I failed to find a "
                + dirName
                + " directory upwards\n"
                + "   and could not create one!");
        ui.showMessage(
                " You can still run the application, but note your data\n"
                    + "   will not be here the next time you restart the app!");
    }
}
