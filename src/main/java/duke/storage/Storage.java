package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a storage object.
 */
public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads the user's stored list.
     * @return An arraylist containing the user's stored list.
     * @throws DukeException if there is an issue with file access.
     */
    public ArrayList<Task> load() throws DukeException {
        BufferedReader reader;
        ArrayList<Task> storedTasks = new ArrayList<>(100);

        try {
            String line;
            reader = new BufferedReader(new FileReader(filepath));

            while ((line = reader.readLine()) != null) {
                Task task = stringToTask(line);
                storedTasks.add(task);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new DukeException("IO Exception");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } // End trycatch.
        return storedTasks;
    }

    /**
     * Converts the stored string to a task.
     * @param str A string representing a task.
     * @return A task.
     * @throws DukeException if the stored data is corrupted.
     */
    public Task stringToTask(String str) throws DukeException {
        String[] split = str.split("\\|");
        try {
            boolean isDone = (split[0].trim()).equals("1");
            switch (split[1].trim()) {
            case "T": // Todo.
                return new Todo(split[2].trim()).setDone(isDone);
            case "D": // Deadline
                return new Deadline(split[2].trim(), split[3].trim()).setDone(isDone);
            case "E":
                return new Event(split[2].trim(), split[3].trim()).setDone(isDone);
            default:
                throw new DukeException("Error in savefile!!");
            } // End switch.
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error in savefile!!");
        } // End try-catch.
    }

    /**
     * Writes the user list into a text file.
     * @param tasks An array list containing the user's list.
     * @throws DukeException if writing was unsuccessful.
     */
    public void store(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter filewriter = new FileWriter(filepath, false);
            filewriter.write("");
            filewriter = new FileWriter(filepath, true);
            for (Task task : tasks) {
                filewriter.write(task.toStorage() + "\n");
            } // End for loop
            filewriter.close();

        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        } catch (IOException e) {
            throw new DukeException("IO Exception");
        } // End try-catch.

    }
}
