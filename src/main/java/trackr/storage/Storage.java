package trackr.storage;

import trackr.task.Deadline;
import trackr.task.Event;
import trackr.task.Task;
import trackr.task.Todo;
import trackr.tasklist.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /**
     * Path of .txt file where tasks are stored persistently.
     */
    String filePath;

    /**
     * Class constructor that assigns filepath to the object.
     * @param filePath Path of .txt file where tasks are stored persistently
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method loads the stored task list from a previous session.
     * @return ArrayList List of tasks
     * @throws FileNotFoundException On wrong filepath used
     */
    public ArrayList<Task> load() throws IOException {
        File f = new File(filePath);
        f.createNewFile();
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] data = s.split(" \\| ");
            String command = data[0];
            if (command.equals("T")) {
                Todo t = new Todo(data[2]);
                if (Integer.parseInt(data[1]) == 1) {
                    t.setDone();
                }
                tasks.add(t);
            } else if (command.equals("D")) {
                Deadline d = new Deadline(data[2], data[3]);
                if (Integer.parseInt(data[1]) == 1) {
                    d.setDone();
                }
                tasks.add(d);
            } else {
                Event e = new Event(data[2], data[3]);
                if (Integer.parseInt(data[1]) == 1) {
                    e.setDone();
                }
                tasks.add(e);
            }
        }
        return tasks;
    }

    /**
     * This method appends new tasks to the end of the file.
     * @param textToAppend New task to be added to end of the file
     */
    public void appendToFile(String textToAppend) {
        try {
            FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }

    /**
     * This method rewrites all data in the file. This is used when we have to edit existing data
     * in the file.
     * @param tasks List of tasks
     */
    public void rewriteFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String textToWrite = "";
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                String type = t.getType();
                if (type.equals("todo")) {
                    String s = "T | ";
                    if (t.getStatus()) {
                        s += "1";
                    } else {
                        s += "0";
                    }
                    s += " | " + t + '\n';
                    textToWrite += s;
                } else if (type.equals("event")) {
                    String s = "E | ";
                    if (t.getStatus()) {
                        s += "1";
                    } else {
                        s += "0";
                    }
                    s += " | " + t + " | " + t.getDate() + '\n';
                    textToWrite += s;
                } else {
                    String s = "D | ";
                    if (t.getStatus()) {
                        s += "1";
                    } else {
                        s += "0";
                    }
                    s += " | " + t + " | " + t.getDate() + '\n';
                    textToWrite += s;
                }
            }
            fw.write(textToWrite);
            fw.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }
}
