package duke.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Task.TaskType;
import duke.task.ToDo;

/**
 * Encapsulates the database of duke.Duke bot.
 */
public class DukeDatabase {
    private static final int DONE = 1;
    private static final int NOT_DONE = 0;
    private static final String corruptedDataMessage = "Database has corrupted data!";

    private String databaseDirectory; // Path of the the database file.
    private File tasksFile; // File object which represents the database file of duke.Duke bot

    // Cannot delete this constructor to ensure that it overwrites the default public constructor
    private DukeDatabase() {
    }

    /**
     * Returns a DukeDatabase object.
     *
     * @return a DukeDatabase object.
     */
    public static DukeDatabase getDukeDatabaseInstance() {
        return new DukeDatabase().initialise();
    }

    /**
     * Initializes the database by loading the database file and attach it to a File object.
     *
     * @return the database object itself.
     */
    private DukeDatabase initialise() {
        setDatabaseDirectory();
        createDataFile();

        return this;
    }

    /**
     * Set the directory of the data text file used to record the tasks of the user.
     */
    private void setDatabaseDirectory() {
        String currDirectory = new File(System.getProperty("user.dir")).getPath();
        databaseDirectory = String.format("%s/data/duke.txt", currDirectory);
    }

    /**
     * Creates a data text file to store the tasks of the user if it has not existed.
     */
    private void createDataFile() {
        try {
            tasksFile = new File(databaseDirectory);
            tasksFile.getParentFile().mkdirs(); // create the /data directory if it has not exist
            tasksFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all the tasks stored in the database.
     *
     * @return a list of all the tasks stored in the database.
     */
    public TaskList getAllTasks() {
        TaskList tasksList = new TaskList();

        try {
            File f = new File(databaseDirectory);
            Scanner sc = new Scanner(f);

            // Read the database file.
            while (sc.hasNextLine()) {
                try {
                    // Create the corresponding task object from the data in the database
                    // and add into the taskList.
                    tasksList.addTask(createTask(sc.nextLine()));
                } catch (DukeException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return tasksList;
    }

    /**
     * Creates the corresponding task object from a line of data in the database.
     *
     * @param dataInput a line of data in the database.
     * @return the corresponding task object.
     * @throws DukeException If the given data is corrupted.
     */
    private Task createTask(String dataInput) throws DukeException {
        TaskType type = getType(dataInput);
        String[] details = getDetails(dataInput);

        // Create the corresponding task object.
        Task task;
        if (type == TaskType.TODO) {
            task = createToDo(details);
        } else if (type == TaskType.DEADLINE) {
            task = createDeadline(details);
        } else if (type == TaskType.EVENT) {
            task = createEvent(details);
        } else {
            throw new DukeException("Database has corrupted data!");
        }

        // Check and update the status of the task accordingly.
        boolean isDone = isDone(dataInput);
        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Updates the database file (used at the end of the program).
     *
     * @param tasks a list of tasks as updated at the point when the program ends.
     */
    public void update(TaskList tasks) {
        assert tasks != null : "Tasks list given cannot be null!";

        // Extract all the data summaries of the tasks and append them into a single string.
        StringBuilder bldr = new StringBuilder(150);
        tasks.forEach(t -> {
            bldr.append(String.format("%s\n", t.getSummaryForDatabase()));
        });

        // Write all the data summaries into the database file.
        try {
            FileWriter fw = new FileWriter(databaseDirectory);
            fw.write(bldr.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the type of the task corresponding to the data given.
     *
     * @param data data of the task stored in the database file.
     * @return the type of the task corresponding to the data given.
     * @throws DukeException if the data is corrupted.
     */
    private TaskType getType(String data) throws DukeException {
        char type = data.charAt(0);

        switch (type) {
        case ('T'):
            return TaskType.TODO;
        case ('D'):
            return TaskType.DEADLINE;
        case ('E'):
            return TaskType.EVENT;
        default:
            throw new DukeException("Database has corrupted data!");
        }
    }

    /**
     * Returns true if the task associated to the data is done.
     *
     * @param data data of a task stored in the database file.
     * @return true if the task associated to the data is done.
     * @throws DukeException if the data is corrupted.
     */
    private boolean isDone(String data) throws DukeException {
        String[] details = getDetails(data);
        int status = Integer.parseInt(details[1].trim());

        switch (status) {
        case DONE:
            return true;
        case NOT_DONE:
            return false;
        default:
            throw new DukeException(corruptedDataMessage);
        }
    }

    /**
     * Returns the details of the task corresponding to the data given.
     *
     * @param data data of a task stored in the database file.
     * @return the details of the task, stored in an array.
     */
    private String[] getDetails(String data) {
        return data.split("\\|");
    }

    /**
     * Creates a To Do object based on the given details of the To Do object.
     *
     * @param details details of the To Do object.
     * @return a To Do object corresponding to the details given.
     */
    private ToDo createToDo(String[] details) {
        String title = details[2].trim();

        return new ToDo(title);
    }

    /**
     * Creates a Deadline object based on the given details of the Deadline object.
     *
     * @param details details of the Deadline object.
     * @return a Deadline object corresponding to the details given.
     */
    private Deadline createDeadline(String[] details) {
        String title = details[2].trim();
        String date = details[3].trim();

        return new Deadline(title, date);
    }

    /**
     * Creates an Event object based on the given details of the Event object.
     *
     * @param details details of the Event object.
     * @return an Event object corresponding to the details given.
     */
    private Event createEvent(String[] details) {
        String title = details[2].trim();
        String date = details[3].trim();

        boolean isTentative =  date.contains(";");

        if (isTentative) {
            String[] tentativeDates = date.split("; ");
            return new Event(title, tentativeDates);
        } else {
            return new Event(title, date);
        }
    }
}
