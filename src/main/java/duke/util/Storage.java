package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    /**
     * Constructor for the flat file database storage.
     *
     * @param filePath File location for the flat file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the database.
     *
     * @param tasks The full list of tasks to be saved
     */
    public void save(ArrayList<Task> tasks) throws IOException, DukeException {
        FileWriter fw = new FileWriter(filePath);
        try {
            for (Task t : tasks) {
                fw.write(t.printSave() + System.lineSeparator());
            }
        } catch (IOException ex) {
            throw new DukeException("Error [Storage] 0x0000003: Save Failed.");
        }
        fw.close();
    }

    /**
     * Loads the database.
     */
    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File dataFile = new File(filePath);
        Scanner sc = new Scanner(dataFile);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] lineSplit = line.split(" \\| ");
            try {
                switch (line.charAt(0)) {
                    case 'T':
                        tasks.add(new Todo(lineSplit[2]));
                        break;
                    case 'D':
                        tasks.add(new Deadline(lineSplit[2], lineSplit[3]));
                        break;
                    case 'E':
                        tasks.add(new Event(lineSplit[2], lineSplit[3]));
                        break;
                    default:
                        throw new DukeException("Error [Storage] 0x0000001: Invalid or Malformed Data.");
                }
                if (lineSplit[1].equals("1")) {
                    tasks.get(tasks.size() - 1).done();
                }
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("Error [Storage] 0x0000002: Invalid or Malformed Data.");
            }
        }
        return tasks;
    }
}
