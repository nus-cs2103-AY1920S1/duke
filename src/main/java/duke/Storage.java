package duke;

import exception.DukeException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public String filePath;
    private Scanner sc;

    /**
     * Initialises a storage object which handles with loading tasks from the file
     * and saving tasks in the file.
     * @param filePath a string storing the location of the text file which stores the tasks' data.
     * @throws IOException IOException if an I/O error occurs opening the source
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        File file = new File(filePath);
        if (file.exists()) {
            sc = new Scanner(file);
        } else {
            file.createNewFile();
        }
    }

    /**
     * Loads a list of tasks in the text file.
     * @return an ArrayList of tasks.
     * @throws DukeException DukeException that may arise from invalid inputs.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String tokenString = sc.nextLine();
            String[] token = tokenString.split("\\|\\|");
            String time;
            Task task;
            switch (token[0].trim()) {
            case "T":
                task = new ToDoTask(token[2].trim());
                break;
            case "D":
                time = token[3];
                task = new DeadlineTask(token[2].trim(), time);
                break;
            case "E":
                time = token[3];
                task = new EventTask(token[2].trim(), time);
                break;
            default:
                System.out.println(tokenString);
                throw new DukeException("Corrupted file");
            }
            tasks.add(task);
            if (token[1].trim().equals("1")) {
                task.markAsDone();
            }
        }
        return tasks;
    }

    /**
     * Saves tasks in the text file given a TaskList.
     * @param tasks TaskList to be saved into the text file.
     * @throws IOException IOException if an I/O error occurs when writing onto the file.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw1 = new FileWriter(filePath);
        fw1.write("");
        fw1.close();
        FileWriter fw2 = new FileWriter(filePath);
        for (Task task : tasks.getList()) {
            String s = task.toFileString() + "\n";
            fw2.write(s);
        }
        fw2.close();
    }
}
