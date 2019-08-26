package storage;

import exception.DukeException;
import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage responsible for interacting with user's hard disk storage.
 */
public class Storage {

    private Scanner sc;
    private String filepath;

    /**
     * Storage Constructor.
     * @param filepath the file location in local pc.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        try {
            this.sc = new Scanner(new File(this.filepath));
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    /**
     * Read data from hard disk storage.
     * @return an array list of tasks, which will be passed into TaskList.
     * @throws DukeException is thrown if problems occur during file reading.
     * @see TaskList
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            while (sc.hasNext()) {
                String[] input = sc.nextLine().split("\\|");

                //strip any leading spaces
                for (int i = 0; i < input.length; i++) {
                    input[i] = input[i].strip();
                }

                if (input[0].equals("[T]")) {
                    tasks.add(new ToDo(input[1], input[2]));
                } else if (input[0].equals("[D]")) {
                    tasks.add(new Deadline(input[1], input[2], input[3]));
                } else { //Event task
                    tasks.add(new Event(input[1], input[2], input[3]));
                }
            }
            return tasks;
        } catch (ParseException e) {
            throw new DukeException("Load fails");
        }
    }

    /**
     * Store tasks list into hard disk storage.
     * @param tasks the tasks list to store.
     */
    public void save(TaskList tasks) {
        try {
            PrintWriter pw = new PrintWriter(filepath);
            pw.close();
            PrintWriter pw2 = new PrintWriter(filepath);
            for (Task task : tasks.getTaskList()) {
                pw2.println(task.toDataBase());
            }
            pw2.close();
        } catch (FileNotFoundException e){
            System.err.println(e);
        }
    }
}