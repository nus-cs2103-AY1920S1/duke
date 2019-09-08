package duke.component;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Encapsulates the database of duke.Duke bot.
 */
public class DukeDatabase {
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
        String projectRoot = new File(System.getProperty("user.dir"))
                .getParentFile()
                .getPath();
        databaseDirectory = String.format("%s/data/duke.txt", projectRoot);
    }

    /**
     * Creates a data text file to store the tasks of the user if it has not existed.
     */
    private void createDataFile() {
        try {
            tasksFile = new File(databaseDirectory);
            tasksFile.getParentFile().mkdirs();
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
        // Get the type of the Task.
        String[] arr = dataInput.split("\\|");
        String type = arr[0].trim();

        // Create the corresponding task object.
        Task task;
        if ("T".equals(type)) {
            task = new ToDo(arr[2].trim());
        } else if ("D".equals(type)) {
            task = new Deadline(arr[2].trim(), arr[3].trim());
        } else if ("E".equals(type)) {
            task = new Event(arr[2].trim(), arr[3].trim());
        } else {
            throw new DukeException("Database has corrupted data!");
        }

        // Check and update the status of the task accordingly.
        int status = Integer.parseInt(arr[1].trim());
        if (status == 1) {
            task.markAsDone();
        } else if (status != 0) {
            throw new DukeException("Database has corrupted data!");
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
}
