package duke.handler;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

/**
 * The Storage class deals with the saving and loading of data into Duke.
 * The data are the tasks that the user specified.
 */
public class Storage {
    /**
     * The path of the file where data are stored.
     */
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the data into the specified filepath.
     * @param list The list of tasks to be saved.
     * @throws IOException if file cannot be found or is corrupted
     */
    public void save(ArrayList<Task> list) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (i > 0) {
                writer.newLine();
            }
            writer.write(task.toString());
        }
        writer.close();
    }

    /**
     * Loads data from the specified filepath.
     * @return The list of tasks that were loaded.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            String read;
            while ((read = reader.readLine()) != null) {
                String type = read.substring(1, 2);
                String status = read.substring(4, 5);
                String info = read.substring(7);

                Task task;
                if (type.equals("T")) {
                    task = new ToDo(info.trim());
                    if (status.equals("\u2713")) {
                        task.markAsDone();
                    }
                    list.add(task);
                } else if (type.equals("E")) {
                    String[] infoArr = info.split("at:");
                    String description = infoArr[0].substring(0, infoArr[0].length() - 1).trim();
                    String at = infoArr[1].substring(1, infoArr[1].length() - 1);
                    task = new Event(description, at);
                    if (status.equals("Y")) {
                        task.markAsDone();
                    }
                    list.add(task);
                } else if (type.equals("D")) {
                    String[] infoArr = info.split("by:");
                    String description = infoArr[0].substring(0, infoArr[0].length() - 1).trim();
                    String by = infoArr[1].substring(1, infoArr[1].length() - 1);
                    task = new Deadline(description, by);
                    if (status.equals("\u2713")) {
                        task.markAsDone();
                    }
                    list.add(task);
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return list;
    }
}