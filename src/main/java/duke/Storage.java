package duke;

import exception.CorruptedDataException;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads, parses and saves data from/to specified file destination.
 */
public class Storage {
    private String filepath;

    /**
     * Creates Storage object with specified file destination.
     * @param filepath File destination.
     */
    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads the tasks from existing list in the file.
     * @return List of existing tasks.
     * @throws FileNotFoundException Specified file is not found.
     * @throws ParseException Invalid variable to parse.
     * @throws DukeException Invalid actions / missing values.
     */
    ArrayList<Task> load() throws IOException, ParseException, DukeException {
        // pass the path to the file as a parameter
        File file = new File(filepath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        Scanner s = new Scanner(file);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        ArrayList<Task> tasks = new ArrayList<>();

        while (s.hasNextLine()) {
            String[] task = s.nextLine().split(" \\| ");
            Task taskToAdd;

            switch (task[0]) {
            case "D":
                taskToAdd = new Deadline(task[2], task[1].equals("+"), formatter.parse(task[3]));
                break;
            case "E":
                taskToAdd = new Event(task[2], task[1].equals("+"), formatter.parse(task[3]));
                break;
            case "T":
                taskToAdd = new Todo(task[2], task[1].equals("+"));
                break;
            default:
                throw new CorruptedDataException();
            }
            tasks.add(taskToAdd);
        }

        assert tasks.size() > 0 : "Error in loading";
        return tasks;
    }

    /**
     * Saves the current list of tasks into the file.
     * @param tasks List of tasks.
     * @throws IOException Error in writing to file.
     */
    void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter("data/duke.txt");
        for (Task task: tasks) {
            writer.write(task.toFile() + System.lineSeparator());
        }
        writer.close();
    }
}
