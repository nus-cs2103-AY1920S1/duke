package duke.main;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.exception.DukeException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

/**
 * Deals with loading tasks from the file and saving tasks to the file
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks saved into the hard drive from the previous Duke session
     * and stores them in an ArrayList of tasks.
     *
     * @return an ArrayList of existing tasks
     * @throws DukeException if file cannot be found or opened
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String[] task = sc.nextLine().split(" \\| ");
                switch (task[0]) {
                case "[T]":
                    tasks.add(new Todo(task[2], (task[1].equals("[\u2713]") ? true : false)));
                    break;
                case "[D]":
                    tasks.add(new Deadline(task[2], task[3].substring(4), (task[1].equals("[\u2713]") ? true : false)));
                    break;
                case "[E]":
                    tasks.add(new Event(task[2], task[3].substring(4), (task[1].equals("[\u2713]") ? true : false)));
                    break;
                }
            }
            return tasks;
        }
        catch (Exception e) {
            throw new DukeException("OOPS!!! " + e.getMessage());
        }
    }

    public void appendToFile(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt", true);
            fw.append(task.toString() + "\n");
            fw.close();
        }
        catch (Exception e) {
            throw new DukeException("OOPS!!!" + e.getMessage());
        }
    }

    public void writeToFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            ArrayList<Task> added = tasks.getTasks();
            for (int i = 0; i < added.size(); i++) {
                fw.write(added.get(i).toString() + "\n");
            }
            fw.close();
        }
        catch (Exception e) {
            throw new DukeException("OOPS!!!" + e.getMessage());
        }
    }
}
