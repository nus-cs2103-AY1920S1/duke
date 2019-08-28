package duke.main;

import duke.task.*;
import duke.exception.DukeException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String[] task = sc.nextLine().split(" | ");
                switch (task[0]) {
                case "[T]":
                    tasks.add(new Todo(task[2], (task[1].equals("\u2713") ? true : false)));
                    break;
                case "[D]":
                    tasks.add(new Deadline(task[2], task[3].substring(4), (task[1].equals("\u2713") ? true : false)));
                    break;
                case "[E]":
                    tasks.add(new Event(task[2], task[3].substring(4), (task[1].equals("\u2713") ? true : false)));
                    break;
                }
            }
            return tasks;
        }
        catch (Exception e) {
            throw new DukeException("OOPS!!!" + e.getMessage());
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
