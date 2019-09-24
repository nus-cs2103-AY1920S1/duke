package seedu.duke.storage;

import seedu.duke.core.DukeException;
import seedu.duke.statistic.Statistic;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Storage class is used to read and write data from the text file.
 * filePath attribute stores the String of the absolute path of the text file.
 */
public class Storage {
    private String filepath;

    /**
     * Constructor for the Storage class.
     *
     * @param filepath Absolute filepath of the text file.
     *                 Eg. "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\obot_data\\tasks.txt".
     */
    public Storage(String filepath) {

        this.filepath = System.getProperty("user.dir") + filepath;
    }

    /**
     *  Creates a text file at that location, if the text file does not exist at the specified location.
     *
     * @param filepath Absolute filepath of the text file.
     * @throws IOException An IOException may occur when trying to write the file.
     */
    public void createFile(String filepath) throws IOException {
        assert filepath != null : "Filepath should not be null";
        File f = new File(filepath);
        String data = "";

        // If the file does not exist, creates a new text file.
        if (!f.exists()) {
            try {
                Files.write(Paths.get(filepath), data.getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Appends the String text to the text file.
     *
     * @param text The text to be appended to the txt file.
     * @throws IOException An IOException may occur when trying to write the file.
     */
    public void writeToTaskFile(String text) throws IOException {
        assert !text.isEmpty() : "Text to be written should not be empty";
        FileWriter fw = new FileWriter(this.getFilePath(), true);
        fw.write(text + System.lineSeparator());
        fw.close();
    }

    /**
     * Saves the Statistic object to the txt file.
     *
     * @param stat Statistics object.
     * @throws IOException Error thrown in writing the file.
     */
    public void saveStatFile(Statistic stat) throws IOException {
        clearStatFileBeforeSaving();

        FileWriter fw = new FileWriter(this.getFilePath(), true);
        fw.write("totalCommandsExecuted " + " : " + stat.getTotalCommandsExecuted() + System.lineSeparator());
        fw.write("totalTasksDeleted " + " : " + stat.getTotalTasksDeleted() + System.lineSeparator());
        fw.write("totalTodosCompleted " + " : " + stat.getTotalTodosCompleted() + System.lineSeparator());
        fw.write("totalDeadlinesCompleted " + " : " + stat.getTotalDeadlinesCompleted() + System.lineSeparator());
        fw.write("totalEventsCompleted " + " : " + stat.getTotalEventsCompleted() + System.lineSeparator());

        fw.close();

    }

    /**
     * Clears and appends headers to the text file.
     *
     * @throws IOException An IOException may occur when trying to write the file.
     */
    public void clearTaskFileBeforeSaving() throws IOException {
        // Overwrites text file and adds headers before saving tasks
        FileWriter fw = new FileWriter(this.getFilePath(), false);
        fw.write("event type | isDone | description | extra description | dateCreated | lastModified"
                + System.lineSeparator());
        fw.close();
    }

    /**
     * Clears and appends headers to the Statistics file.
     * @throws IOException An IOException may occur when trying to write the file.
     */
    public void clearStatFileBeforeSaving() throws IOException {
        FileWriter fw = new FileWriter(this.getFilePath(), false);
        fw.write("statistic | integerValue" + System.lineSeparator());
        fw.close();
    }

    /**
     * Checks if the 'data' folder is present in the working directory.
     *
     * @return Boolean.
     */
    public Boolean folderExists() {
        String dataFolder = System.getProperty("user.dir") + "\\obot_data\\";
        File f = new File(dataFolder);
        if (f.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Creates the data folder.
     */
    public void createDataFolder() {
        String dataFolder = System.getProperty("user.dir") + "\\obot_data\\";
        File f = new File(dataFolder);
        f.mkdirs();
    }

    /**
     * Checks if the file (specified in filePath) exists.
     *
     * @return Boolean.
     */
    public Boolean fileExists() {
        File f = new File(getFilePath());
        if (f.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns an ArrayList(Task) from the data loaded from the filePath.
     *
     * @return ArrayList(Task) parsed from text file.
     * @throws FileNotFoundException An FilenotFoundException may occur when if filePath is invalid.
     * @throws DukeException Custom error.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException, DukeException, IOException {

        if (!folderExists()) {
            createDataFolder();
            createEmptyTaskFile();
        } else {
            if (!fileExists()) {
                createEmptyTaskFile();
            }
        }

        // Initialises variables to handle the txt input file.
        ArrayList<String> inputsFromFile = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            // Creates a scanner object to read the txt file from filePath.
            Scanner scanner = new Scanner(new File(getFilePath()));


            while (scanner.hasNextLine()) {

                inputsFromFile.add(scanner.nextLine());
            }

            for (String input : inputsFromFile) {
                // A Possible input string is: "D | 0 | CS2103 Ip  | Wed 2359".
                String[] words = input.split("\\|");
                Boolean isDone = false;

                String taskType = words[0].trim();

                switch (taskType) {
                case "T":

                    Todo newTodo = newTodo(words);
                    tasks.add(newTodo);
                    break;

                case "E":

                    Event newEvent = newEvent(words);
                    tasks.add(newEvent);
                    break;

                case "D":

                    Deadline newDeadline = newDeadline(words);
                    tasks.add(newDeadline);
                    break;

                default:
                    // String is not a task. Ignore
                    // throw new DukeException("Unable to read from saved file");
                }

            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found (Storage.java > loadTasks");
        }
        return tasks;
    }

    /**
     * Creates a tree map from the txt file.
     *
     * @return Tree Mapping of String to Integer.
     * @throws FileNotFoundException Thrown when file not found.
     */
    public TreeMap<String, Integer> loadStats() throws DukeException, IOException {

        if (!folderExists()) {
            createDataFolder();
            createEmptyStatFile();
        } else  {
            if (!fileExists()) {
                createEmptyStatFile();
            }
        }

        ArrayList<String> inputsFromFile = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();

        try {
            Scanner scanner = new Scanner(new File(getFilePath()));

            while (scanner.hasNextLine()) {
                inputsFromFile.add(scanner.nextLine());
            }

            for (String input : inputsFromFile) {
                if (input.contains(":")) {
                    String[] words = input.split(":");
                    map.put(words[0].trim(), Integer.parseInt(words[1].trim()));
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Stats file not found, Storage.java, loadStats()");
        }
        return map;
    }

    /**
     * Creates empty stat file for initialization.
     *
     * @throws DukeException If file cannot be created.
     */
    public void createEmptyStatFile() throws DukeException {
        String absoluteFilepath = getFilePath();
        try {
            String data = "statistic | integerValue" + System.lineSeparator() + "totalCommandsExecuted  : 0"
                    + System.lineSeparator() + "totalTasksDeleted  : 0" + System.lineSeparator()
                    + "totalTodosCompleted  : 0" + System.lineSeparator() + "totalDeadlinesCompleted  : 0"
                    + System.lineSeparator() + "totalEventsCompleted  : 0" + System.lineSeparator();
            Files.write(Paths.get(absoluteFilepath), data.getBytes());
        } catch (IOException e) {
            String errorMessage = "Empty file: " + absoluteFilepath + " unable to be created";
            System.out.println(errorMessage);
            throw new DukeException(errorMessage);
        }
    }

    /**
     * Creates an empty file if file is not present during reading. Since saving data will always overwrite whole file,
     * it suffices for the file to merely be present and not formatted correctly.
     */
    public void createEmptyTaskFile() throws DukeException {
        String absoluteFilepath = getFilePath();
        try {
            String data = "event type | isDone | description | extra description | dateCreated | lastModified"
                    + System.lineSeparator();
            Files.write(Paths.get(absoluteFilepath), data.getBytes());
        } catch (IOException e) {
            String errorMessage = "Empty file: " + absoluteFilepath + " unable to be created";
            System.out.println(errorMessage);
            throw new DukeException(errorMessage);
        }
    }

    /**
     * Returns the filePath attribute.
     *
     * @return String filePath attribute.
     */
    public String getFilePath() {
        return this.filepath;
    }

    /**
     * Returns a new To do object from the String array read from file.
     *
     * @param words String array.
     * @return To do object.
     */
    public Todo newTodo(String[] words) {
        Boolean isDone = false;

        if (words[1].contains("1")) {
            isDone = true;
        } else if (words[1].contains("0")) {
            isDone = false;
        }

        String description = "";
        description = words[2].trim();
        String createDateTime = "";
        createDateTime = words[4].trim();
        String lastModifiedDateTime = "";
        lastModifiedDateTime = words[5].trim();


        Todo newTodo = new Todo(description, isDone, LocalDateTime.parse(createDateTime),
                LocalDateTime.parse(lastModifiedDateTime));

        return newTodo;
    }

    /**
     * Returns a new Event object from the String array read from file.
     *
     * @param words String array.
     * @return Event object.
     */
    public Event newEvent(String[] words) {
        Boolean isDone = false;

        if (words[1].contains("1")) {
            isDone = true;
        } else if (words[1].contains("0")) {
            isDone = false;
        }
        String description = "";
        description = words[2].trim();
        String extraDescription = "";
        extraDescription = words[3].trim();
        String createDateTime = "";
        createDateTime = words[4].trim();
        String lastModifiedDateTime = "";
        lastModifiedDateTime = words[5].trim();

        Event newEvent = new Event(description, extraDescription, isDone,
                LocalDateTime.parse(createDateTime), LocalDateTime.parse(lastModifiedDateTime));

        return newEvent;
    }

    /**
     * Returns a new Deadline object from the String array read from file.
     *
     * @param words String array.
     * @return Event object.
     */
    public Deadline newDeadline(String[] words) throws DukeException {
        Boolean isDone = false;

        if (words[1].contains("1")) {
            isDone = true;
        } else if (words[1].contains("0")) {
            isDone = false;
        }
        String description = "";
        description = words[2].trim();
        String extraDescription = "";
        extraDescription = words[3].trim();
        String createDateTime = "";
        createDateTime = words[4].trim();
        String lastModifiedDateTime = "";
        lastModifiedDateTime = words[5].trim();

        Deadline newDeadline = new Deadline(description, extraDescription, isDone,
                LocalDateTime.parse(createDateTime), LocalDateTime.parse(lastModifiedDateTime));

        return newDeadline;
    }

}