package taskpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Scanner;

public class Task {
    protected String description;
    protected boolean isDone;

    public static List<Task> store;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void instantiate() {
        //initialize Task list
        store = new ArrayList<Task>();

        try {
            Scanner sc = new Scanner(new File("../../../data/duke.txt"));

            while (sc.hasNext()) {
                String[] input = sc.nextLine().split("\\|");

                //strip any leading spaces
                for (int i = 0; i < input.length; i++) {
                    input[i] = input[i].strip();
                }

                if (input[0].equals("[T]")) {
                    store.add(new ToDo(input[1], input[2]));
                } else if (input[0].equals("[D]")) {
                    store.add(new Deadline(input[1], input[2], input[3]));
                } else { //Event task
                    store.add(new Event(input[1], input[2], input[3]));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    public static void storeToDatabase() {
        try {
            PrintWriter pw = new PrintWriter("../../../data/duke.txt");
            pw.close();
            PrintWriter pw2 = new PrintWriter("../../../data/duke.txt");
            for (Task task : store) {
                pw2.println(task.toDataBase());
            }
            pw2.close();
        } catch (FileNotFoundException e){
            System.err.println(e);
        }
    }

    //Will be Overridden in child class
    protected String toDataBase() {
        return "";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}