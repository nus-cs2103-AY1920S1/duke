import java.io.FileWriter;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

/**
 *  Represents the file to load-up and save the TaskList.
 */

public class Storage {

    private String filepath;

    /**
     * Constructs a storage object.
     * @param filepath refers to the file path of TaskList
     */

    public Storage (String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads up the tasklist from the saved file in the harddisk.
     * @return ArrayList of tasks.
     * @throws DukeException if the file is not found.
     */

    public ArrayList<Task> load() throws DukeException{
        ArrayList<Task> tasks = new ArrayList<>();
        String line;
        try {
            File loadupFile = new File(this.filepath);
            Scanner scLoad = new Scanner(loadupFile);
            while (scLoad.hasNextLine()) {
                String sentence = scLoad.nextLine();
                String[] taskInfo = sentence.split(" \\u007C ");
                if (taskInfo[0].equals("T")) {
                    Task t = new ToDo(taskInfo[2]);
                    if (taskInfo[1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                } else if (taskInfo[0].equals("D")) {
                    Task t = new Deadline(taskInfo[2], taskInfo[3]);
                    if (taskInfo[1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                } else if (taskInfo[0].equals("E")) {
                    Task t = new Event(taskInfo[2], taskInfo[3]);
                    if (taskInfo[1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                } else {

                }
            }
            scLoad.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Error, File Not Found.");
        }
        return tasks;
    }

    public void writeToHardDisk() {
        try {
            String taskListFilePath = "C:\\repos\\duke\\out\\data\\TaskList.txt";
            FileWriter fw = new FileWriter (taskListFilePath);
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i) instanceof Deadline) {
                    writeToFile(taskListFilePath, "D | " + tasks.get(i).isDone() + " | "
                            + tasks.get(i).getTask() + " | " + ((Deadline) tasks.get(i)).getDueDate());
                } else if (tasks.get(i) instanceof Event) {
                    writeToFile(taskListFilePath, "E | " + tasks.get(i).isDone() + " | "
                            + tasks.get(i).getTask() + " | " + ((Event) tasks.get(i)).getDueDate());
                } else {
                    writeToFile(taskListFilePath, "T | " + tasks.get(i).isDone() + " | "
                            + tasks.get(i).getTask());
                }
                if (i != tasks.size() - 1) {
                    writeToFile(taskListFilePath, "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred " + e.getMessage());
        }
    }
}
