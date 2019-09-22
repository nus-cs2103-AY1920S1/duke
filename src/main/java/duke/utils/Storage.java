package duke.utils;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implements the logic required to save Tasks. This class abstracts away the details
 * required for saving Tasks onto disk as well as reading the disk data and converting
 * the data back into Task objects. The abstraction enables any user of the class to simply
 * use the <code>save</code> and <code>load</code> methods.
 */
public class Storage {
    private File file;

    /**
     * Constructor.
     *
     * @param filepath String representing the path to the file in which Task data will be saved
     */
    public Storage(String filepath) {
        this.file = new File(filepath);
    }

    /**
     * Convenience method to save a TaskList onto disk.
     *
     * @param allTasks TaskList representing a collection of Task objects
     * @throws DukeException thrown when error encountered saving to file.
     */
    public void save(TaskList allTasks) throws DukeException {
        try {
            Path path = Paths.get(Duke.saveFilePath);
            Files.deleteIfExists(path);

            File f = new File(Duke.saveFilePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            FileWriter fw = new FileWriter(Duke.saveFilePath, true);

            ArrayList<Task> allTasksArrList = allTasks.getArrayList();
            for (Task t : allTasksArrList) {
                fw.write(t.getStorageFormat() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Could not save to file!");
        }

    }

    /**
     * Convenience method to load Task data from disk and re-created the TaskList object.
     *
     * @return TaskList representing a collection of Tasks saved on disk
     * @throws DukeException thrown when no existing tasks were saved to file.
     */
    public TaskList load() throws DukeException {
        try {
            Scanner sc = new Scanner(this.file);
            ArrayList<Task> allStoredTasks = new ArrayList<Task>();
            while (sc.hasNext()) {
                Task t = generateSavedTask(sc.nextLine());
                allStoredTasks.add(t);
            }
            return new TaskList(allStoredTasks);
        } catch (FileNotFoundException e) {
            throw new DukeException("No existing tasks found!"
                + "Unable to create file for saving!");
        }
    }

    /**
     * Private method used to convert from the String format in the disk data.
     * into an actual Task object (ToDo, Event, Deadline)
     *
     * @param nextLine disk data (each field is seperated by the "|" character)
     * @return Task object
     * @throws DukeException re-thrown from underlying method calls.
     */
    private Task generateSavedTask(String nextLine) throws DukeException {
        String[] s = nextLine.split("\\|");
        String command = s[0].trim();
        Task t = new Task("Uninitialised Task");

        //These 2 attributes are consistent across all 3 Task types (ToDo, Deadline, Event)
        boolean isDone = s[1].trim().equals("1");
        String description = s[2].trim();

        switch (command) {
        case "T":
            t = new ToDo(description);
            break;
        case "E":
            String startTime = s[3].trim();
            String endTime = s[4].trim();
            t = new Event(description, startTime, endTime);
            break;
        case "D":
            String deadline = s[3].trim();
            t = new Deadline(description, deadline);
            break;
        default:
            throw new DukeException("Invalid command encountered while generating saved task: " + command);
        }

        if (isDone) {
            t.markAsDone();
        }

        return t;
    }
}
