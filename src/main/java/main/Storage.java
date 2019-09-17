package main;

import task.DateTime;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String filePath;
    private boolean isValidFilePath;

    /**
     * Creates a new Storage object which reads and writes to file at filepath.
     * If file path does not exist, a default task list would be created as follows:
     * [root]/data/tasks.txt
     *
     * @param filePath File path to read from and to write to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        isValidFilePath = file.getAbsoluteFile().exists();
    }

    /**
     * Creates a default Storage object with predetermined file path.
     *
     * @throws DukeException if there is an error in createing the file at the
     * predetermined file path
     */
    public Storage() throws DukeException {
        try {
            filePath = createNewFile();
        } catch (IOException e) {
            isValidFilePath = false;
            throw new DukeException("File cannot be created.");
        }
    }

    /**
     * Returns true if file path is valid.
     *
     * @return true if file path is valid.
     */
    public boolean isValidFilePath() {
        return isValidFilePath;
    }

    /**
     * Makes a directory called Data if it does not exist.
     *
     * @return file path of new data directory.
     */
    public String makeDataDirectoryIfNotExist() {
        String root = new File(System.getProperty("user.dir")).getPath();
        File dataDir = new File(root + "/data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        return dataDir.toString();
    }

    /**
     * Creates a new file at [root]/data/tasks.txt to store the task list.
     *
     * @throws IOException if file cannot be created
     */
    public String createNewFile() throws IOException {
        String dataDir = makeDataDirectoryIfNotExist();
        File taskListFile = new File(dataDir + "/tasks.txt");
        boolean isFileCreated = taskListFile.createNewFile();
        if (isFileCreated) {
            filePath = taskListFile.toString();
            isValidFilePath = true;
            return taskListFile.toString();
        } else {
            return null;
        }
    }

    /**
     * Loads existing task list from specified file path.
     *
     * @return An ArrayList containing the Tasks from the specified file
     * @throws DukeException If file does not exist
     */
    public ArrayList<Task> load() throws DukeException {
        if (!isValidFilePath) {
            return new ArrayList<>();
        }
        File f;
        Scanner sc;
        try {
            f = new File(filePath);
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new DukeException("File loading error: " + e.getMessage());
        }

        ArrayList<Task> taskList = readFromFile(sc, f);
        return taskList;
    }

    /**
     * Reads from a specified File.
     *
     * @param sc    Scanner to read from file
     * @param f     File to be read from
     * @return      The ArrayList of task objects read from file.
     * @throws DukeException If file is corrupted.
     */
    private ArrayList<Task> readFromFile(Scanner sc, File f) throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        while (sc.hasNextLine()) {
            Task task = addToTaskList(sc.nextLine().trim());
            taskList.add(task);
        }
        return taskList;
    }

    private Task addToTaskList(String nextLine) throws DukeException {

        String[] arr = nextLine.split("\\|");

        checkValidTaskListFormat(arr);
        String taskType = checkValidTaskType(arr);
        int status = checkValidTaskStatus(arr);

        boolean isComplete = status != 0;

        Task newTask;
        DateTime dateTime;
        try {
            switch (taskType) {
            case "T":
                newTask = new ToDo(arr[2].trim(), isComplete);
                break;
            case "E":
                dateTime = DateTime.parseTaskListFormat(arr[3].trim());
                newTask = new Event(arr[2].trim(), isComplete, dateTime);
                break;
            case "D":
                dateTime = DateTime.parseTaskListFormat(arr[3].trim());
                newTask = new Deadline(arr[2].trim(), isComplete, dateTime);
                break;
            default:
                newTask = null;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task status found.");
        }

        return newTask;
    }

    private void checkValidTaskListFormat(String[] arr) throws DukeException {
        if (arr.length < 3 || arr.length > 4) {
            throw new DukeException("Existing tasklist is corrupt.");
        }
    }

    private String checkValidTaskType(String[] arr) throws DukeException {
        String taskType = arr[0].trim();
        if (!taskType.equals("T") && !taskType.equals("E") && !taskType.equals("D")) {
            throw new DukeException("Invalid task type found.");
        }
        return taskType;
    }

    private int checkValidTaskStatus(String[] arr) throws DukeException {
        int status;
        try {
            status = Integer.parseInt(arr[1].trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task status found.");
        }
        return status;
    }

    /**
     * Saves current task list to file at specified file path.
     *
     * @param tasks The task list to be written to file.
     * @throws DukeException If a file writing error arises
     */
    public void save(TaskList tasks) throws DukeException {
        if (!isValidFilePath) {
            return;
        }
        try {
            new FileWriter(filePath); //create new file
            for (Task task : tasks.getTasks()) {
                appendToFile(filePath, task.publishTask());
            }
        } catch (IOException e) {
            throw new DukeException("Error in saving to file: " + e.getMessage());
        }
    }

    void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    /**
     * Clears the file found at filePath.
     *
     * @throws DukeException if file is not found.
     */
    public void clearAll() throws DukeException {
        if (!isValidFilePath) {
            return;
        }
        try {
            new FileWriter(filePath); //create new file
        } catch (IOException e) {
            throw new DukeException("File does not exist, there is nothing to clear!");
        }
    }

    public String getFilePath() {
        return filePath;
    }
}