package duke.main;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.exception.DukeException;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

/**
 * Deals with loading tasks from the history file and saving tasks to the history file
 */
public class Storage {
    private File history;

    public Storage(String filePath) {
        try {
            this.history = new File(filePath);
            if (!history.exists()) {
                history.getParentFile().mkdir();
                history.createNewFile();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the tasks saved into the hard drive from the previous Duke session
     * and stores them in an ArrayList of tasks.
     *
     * @return an ArrayList of existing tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner sc = new Scanner(history);
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
            return tasks;
        }
    }

    public void appendToFile(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(history, true);
            fw.append(task.toString() + "\n");
            fw.close();
        }
        catch (Exception e) {
            throw new DukeException("OOPS!!! " + e.getMessage());
        }
    }

    public void writeToFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(history);
            ArrayList<Task> added = tasks.getTasks();
            for (int i = 0; i < added.size(); i++) {
                fw.write(added.get(i).toString() + "\n");
            }
            fw.close();
        }
        catch (Exception e) {
            throw new DukeException("OOPS!!! " + e.getMessage());
        }
    }
}
