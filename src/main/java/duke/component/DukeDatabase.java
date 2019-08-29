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
 * Encapsulates the database of Duke bot.
 */
public class DukeDatabase {
    private String databaseDirectory; // Path of the the database file.
    private File tasksFile; // File object which represents the database file of Duke bot

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
        // Generates the directory for the database
        String projectRoot = new File(System.getProperty("user.dir"))
                .getParentFile()
                .getPath();
        databaseDirectory = String.format("%s/data/duke.txt", projectRoot);

        // Creates the database file and the corresponding directories if they have not existed yet.
        try {
            tasksFile = new File(databaseDirectory);
            tasksFile.getParentFile().mkdirs();
            tasksFile.createNewFile();
        } catch (IOException e) {
            System.out.printf("Fatal Database Error during initialization! Please restart the bot!");
        }

        return this;
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
                } catch (DukeException | NullPointerException | NumberFormatException e) {
                    System.out.println("Database has corrupted data!");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find database file!");
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
            throw new DukeException();
        }

        // Check and update the status of the task accordingly.
        int status = Integer.parseInt(arr[1].trim());
        if (status == 1) {
            task.markAsDone();
        } else if (status != 0) {
            throw new DukeException();
        }

        return task;
    }

    /**
     * Updates the database file (used at the end of the program).
     *
     * @param tasks a list of tasks as updated at the point when the program ends.
     */
    public void update(TaskList tasks) {
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
            System.out.println("Database error occurs when updating data!");
        }
    }
}
