package storage;

import exception.DukeException;
import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private Scanner sc;
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
        try {
            this.sc = new Scanner(new File(this.filepath));
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

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