package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> tasks;
    private File file;

    public Storage(String pathname) {
        this.tasks = new ArrayList<>();
        this.file = new File(pathname);
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] params = input.split(" | ");
                String type = params[0];
                Boolean done = params[1].equals("1");
                String description = params[2];

                Task task;
                switch (type) {
                case "D":
                    String by = params[3];
                    task = new Deadline(description, by);
                    break;

                case "E":
                    String at = params[3];
                    task = new Event(description, at);
                    break;

                case "T":
                    task = new ToDo(description);
                    break;

                default:
                    throw new DukeException("Invalid parameter specified");
                }

                if (done) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            // First time setup
            try {
                FileWriter fw = new FileWriter(file);
                fw.close();
            } catch (IOException ex) {
                throw new DukeException("An error occurred when setting up the storage file");
            }
        }
        return tasks;
    }

    public String stringify() {
        StringBuilder sb = new StringBuilder(tasks.get(0).stringify());
        for (int i = 1; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append(System.lineSeparator());
            sb.append(task.stringify());
        }
        return sb.toString();
    }

    public void save() throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(stringify());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred when saving the file");
        }
    }

    public void addTask(Task task) throws DukeException {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        printTaskCount();

        try {
            FileWriter fw = new FileWriter(file);
            if (tasks.size() > 1) {
                fw.append(System.lineSeparator());
            }
            fw.append(task.stringify());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while saving a new task");
        }

    }

    public void deleteTask(int id) throws DukeException {
        try {
            Task task = tasks.get(id);
            tasks.remove(id);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            printTaskCount();

            save();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Deleting task with ID " + id + " failed: Invalid ID");
        }
    }

    private void printTaskCount() {
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}
