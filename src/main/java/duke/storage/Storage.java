package duke.storage;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Storage class.
 * Instances can write to data file and are
 * responsible for writing to the file for storage purposes.
 */
public class Storage {
    /** Given path to the which holds the data. */
    private String filePath = "../data/duke.txt";

    /**
     * Constructor.
     * Tries to create if it does not exist.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating storage file.");
        }
    }

    /**
     * Writes down all tasks and their state and data.
     * @param tasklist Tasklist that provides the relevant data.
     */
    public void write(TaskList tasklist) {
        try {
            FileWriter fw = new FileWriter(filePath);
            ArrayList<Task> tasks = tasklist.getAllTasks();
            for (Task task : tasks) {
                if (task instanceof ToDo) {
                    fw.write("T");
                    fw.write(" - ");
                    fw.write(task.isDone() ? "1" : "0");
                    fw.write(" - ");
                    fw.write(task.getDescription());
                    fw.write("\n");
                } else if (task instanceof Deadline) {
                    fw.write("D");
                    fw.write(" - ");
                    fw.write(task.isDone() ? "1" : "0");
                    fw.write(" - ");
                    fw.write(task.getDescription());
                    fw.write(" - ");
                    fw.write(((Deadline) task).getBy());
                    fw.write("\n");
                } else if (task instanceof Event) {
                    fw.write("E");
                    fw.write(" - ");
                    fw.write(task.isDone() ? "1" : "0");
                    fw.write(" - ");
                    fw.write(task.getDescription());
                    fw.write(" - ");
                    fw.write(((Event) task).getAt());
                    fw.write("\n");
                } else {
                    System.out.println("Unknown task");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("There was a write error to storage.");
        }
    }

    /**
     * Read data file and gets all the tasks written on it.
     * @return ArrayList of Tasks recorded on data file.
     */
    public ArrayList<Task> parseFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while (line != null) {
                String[] tokens = line.split(" - ");
                String type = tokens[0];
                boolean isDone = tokens[1].equals("1") ? true : false;
                String desc = tokens[2];
                Task t = null;
                switch (type) {
                case "T":
                    t = new ToDo(desc);
                    break;
                case "D":
                    t = new Deadline(desc, tokens[3]);
                    break;
                case "E":
                    t = new Event(desc, tokens[3]);
                    break;
                default:
                    break;
                }
                if (isDone) {
                    t.markAsDone();
                }
                tasks.add(t);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        } finally {
            return tasks;
        }
    }
}
