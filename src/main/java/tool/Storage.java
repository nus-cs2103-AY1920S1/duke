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
    protected String filePath;
    protected FileWriter fw;


    /**
     * Constructor for storage object
     * @param filePath
     */
    public Storage(String filePath) {
        try {
            this.filePath = filePath;
            fw = new FileWriter(filePath, true);
        } catch(IOException e) {
            System.out.println("Error occurred creating new storage object.");
        }

    }

    /**
     * Loads tasks from the .txt file
     * @param commands
     * @return the updated array list of tasks
     */
    public ArrayList<Task> load(ArrayList<Task> commands) {
        try {
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);
            String x;
            int counter = 1;
            while((x = br.readLine()) != null) {
                if (counter == 1) {
                    System.out.println("Here are the tasks in your list:");
                }
                String[] data = x.split("/");
                if (data[0].equals("T")) {
                    Todo t = new Todo(data[2]);
                    if (data[1].equals("1")) {
                        t.markAsDone();
                    }
                    commands.add(t);
                    System.out.println(counter + ". " + t);
                    counter++;
                } else if (data[0].equals("D")) {
                    Deadline d = new Deadline(data[2], new DateTime(data[3]));
                    if (data[1].equals("1")) {
                        d.markAsDone();
                    }
                    commands.add(d);
                    System.out.println(counter + ". " + d);
                    counter++;
                } else if (data[0].equals("E")){
                    Event e = new Event(data[2], new DateTime(data[3]));
                    if (data[1].equals("1")) {
                        e.markAsDone();
                    }
                    commands.add(e);
                    System.out.println(counter + ". " + e);
                    counter++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred loading tasks from file");
        }

        return commands;
    }

    /**
     * Saves the latest task in the .txt file
     * @param Task t
     */
    public void save(TaskList t) {
         try {
             FileWriter fw = new FileWriter(this.filePath);
             ArrayList<Task> tasks = t.getList();
             for (Task task : tasks) {
                 fw.write(task.storageString() + "\n");
             }
             fw.close();
         } catch (IOException e) {
             System.out.println("Error occurred saving task to file.");
         }
    }

}
