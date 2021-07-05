package duke;

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

    private String filePath = ""; // Default is "data/duke.txt"

    /**
     * Create a storage object.
     *
     * @param filePath Specify the location of the file to be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Save the current list to a specified file path on the hard disk.
     *
     * @param tasks List of tasks to be save.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(this.filePath);
            file.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(file);

            String text = "";
            for (int i = 0; i < tasks.size(); i++) {
                //Get tasks
                Task task = tasks.get(i);

                if (task instanceof Todo) {
                    text += "T|" + task.getIsDone() + "|" + task.getDescription();
                } else if (task instanceof Deadline) {
                    text += "D|" + task.getIsDone() + "|" + task.getDescription() + "|"
                        + ((Deadline) task).getDateString();
                } else if (task instanceof Event) {
                    text +=
                        "E|" + task.getIsDone() + "|" + task.getDescription() + "|" + ((Event) task)
                            .getDateString();
                }

                if (i + 1 != tasks.size()) {
                    text += "\n";
                }
            }

            fw.write(text);
            fw.close();
        } catch (IOException ie) {
            new DukeException(
                "Something went wrong when saving. Please ensure the data directory is created.");
        }
    }

    /**
     * Returns a list of tasks from the specified file on the hard disk.
     *
     * @return List of tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> storage = new ArrayList<>();
        try {
            File file = new File(this.filePath); // create a File for the given file path
            file.getParentFile().mkdirs();
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] input = s.nextLine().split("[|]");
                processInput(input, storage);
            }
        } catch (FileNotFoundException fnfe) {
            new DukeException("Unable to load file. Your saved data will not be loaded.");
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            new DukeException("File corrupted. Your saved data will not be loaded.");
        }

        return storage;
    }

    // Processes the input string provided from the text file
    private ArrayList<Task> processInput(String[] input, ArrayList<Task> storage) {
        switch (input[0]) {
        case "T":
            Todo todo = new Todo(input[2]);
            if (input[1].equals("true")) {
                todo.markAsDone();
            }
            storage.add(todo);
            break;
        case "D":
            Deadline deadline = new Deadline(input[2], input[3]);
            if (input[1].equals("true")) {
                deadline.markAsDone();
            }
            storage.add(deadline);
            break;
        case "E":
            Event event = new Event(input[2], input[3]);
            if (input[1].equals("true")) {
                event.markAsDone();
            }
            storage.add(event);
            break;
        default:
            ;
            break;
        }
        return storage;
    }

}
