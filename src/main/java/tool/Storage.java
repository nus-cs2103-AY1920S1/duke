package tool;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;


public class Storage {
    protected String filePath;
    protected FileWriter fw;


    public Storage(String filePath) {
        try {
            this.filePath = filePath;
            fw = new FileWriter(filePath, true);
        } catch(IOException e) {
//            e.printStackTrace();
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
//            e.printStackTrace();
            System.out.println("Error occurred loading tasks from file");
        }

        return commands;
    }

    /**
     * Saves the latest task in the .txt file
     * @param Task t
     */
     protected void save(Task t) {
        try {
            this.fw.append(t.storageString() + System.lineSeparator());
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("Error occurred saving task to file.");
        }
    }

    protected void delete(int i) {
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            FileWriter ff = new FileWriter("./temp.txt", true);
            int j = 1;
            String x;
            while ((x = br.readLine()) != null) {
                if (i != j) {
                    ff.write(x + System.lineSeparator());
                    ff.flush();
                }
                j++;
            }
            ff.close();
            Files.copy(Paths.get("./temp.txt"), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get("./temp.txt"));
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("Error occurred deleting task in file");
        }
    }

    /**
     * Changes the status of task from not done to done
     * @param t
     * @param index
     */
    protected void done(Task t, int index) {
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            FileWriter ff = new FileWriter("./temp.txt", true);
            String x;
            int i = 1;
            while ((x = br.readLine()) != null) {
                if (i == index) {
                    ff.write(t.storageString() + System.lineSeparator());
                } else {
                    ff.write(x + System.lineSeparator());
                }
                i++;
            }
            ff.close();
            Files.copy(Paths.get("./temp.txt"), Paths.get("./duke.txt"), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get("./temp.txt"));
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("Error occurred updating status of task.");
        }
    }

    public void close(TaskList tasks) {
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            FileWriter ff = new FileWriter("./temp.txt", true);
            String x;
            for (Task t : tasks.getList()) {
                    ff.write(t.storageString() + System.lineSeparator());
                    ff.flush();
            }
            ff.close();
            Files.copy(Paths.get("./temp.txt"), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get("./temp.txt"));
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("Error occurred saving tasks to text file.");
        }
    }
}
