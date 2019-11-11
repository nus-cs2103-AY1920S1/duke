package tool;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;


public class Storage {
    private String filePath;
    private FileWriter fw;


    /**
     * Constructor for storage object
     * @param filePath
     */
    public Storage(String filePath) throws DukeException{
        try {
            this.filePath = filePath;
            fw = new FileWriter(filePath, true);
        } catch(IOException e) {
            throw new DukeException("Error occurred creating new storage object.");
        }

    }

    /**
     * Loads tasks from the .txt file
     * @param commands
     * @return the updated array list of tasks
     */
    public ArrayList<Task> load(ArrayList<Task> commands) throws DukeException{
        try {
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);
            String x;
            while((x = br.readLine()) != null) {
                String[] data = x.split("/");
                switch (data[0]) {
                    case "T":
                        Todo t = new Todo(data[2]);
                        if (data[1].equals("1")) {
                            t.markAsDone();
                        }
                        commands.add(t);
                        break;
                    case "D":
                        Deadline d = new Deadline(data[2], new DateTime(data[3]));
                        if (data[1].equals("1")) {
                            d.markAsDone();
                        }
                        commands.add(d);
                        break;
                    case "E":
                        Event e = new Event(data[2], new DateTime(data[3]), new DateTime(data[4]));
                        if (data[1].equals("1")) {
                            e.markAsDone();
                        }
                        commands.add(e);
                        break;
                }
            }
        } catch (IOException e) {
            throw new DukeException("Error occurred loading tasks from file");
        }

        return commands;
    }

    /**
     * Saves the latest task in the .txt file
     * @param (@code) Task t
     */
    public void save(TaskList t) throws DukeException{
         try {
             FileWriter fw = new FileWriter(this.filePath);
             ArrayList<Task> tasks = t.getList();
             for (Task task : tasks) {
                 fw.write(task.storageString() + "\n");
             }
             fw.close();
         } catch (IOException e) {
             throw new DukeException("Error occurred saving task to file.");
         }
    }

}
