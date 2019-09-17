package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

import task.*;

public class Storage {
    private String filepath;
    private FileWriter fileWriter;
    private PrintWriter printWriter;
    private Stack<TaskList> records;

    /**
     * Constructor for Storage objects
     * @param filepath String of the location for data storage.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.records = new Stack<>();
    }

    private Task getData(String[] data){
        Task t = null;
        if (data[0].equals("T")) {
            t = new ToDo(data[2]);
        } else if (data[0].equals("E")) {
            t = new Event(data[2], data[3], data[4]);
        } else if (data[0].equals("D")) {
            t = new Deadline(data[2], data[3]);
        }

        if (data[1].equals("+")) {
            Task.markAsDone(t);
        }
        return t;
    }
    /**
     * Reads tasks from a previous saved file (if present) and store it in a list of tasks.
     * @return ArrayList<Task> a list that contains the tasks from the previous save point
     * (if a previous save point exists). Else an empty list is returned.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File data = new File(filepath);
        try {
            if (data.createNewFile()) {
                System.out.println("    Previous file not found. Creating a new file");
            } else {
                FileReader fr = new FileReader(data);
                BufferedReader bufferedReader = new BufferedReader(fr);
                while (bufferedReader.ready()) {
                    String dataRead = bufferedReader.readLine();
                    String[] dataReads = dataRead.split(" \\| ");
                    Task t = getData(dataReads);
                    tasks.add(t);
                }
                System.out.println("    Previous file is found! Command \"list\" to checkout previous tasks!");
            }
            this.fileWriter = new FileWriter(data, false);
            this.printWriter = new PrintWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * updateTasks updates the file that stores the list of tasks data.
     * @param tasks TaskList. Data in tasks replaces the TaskList data from the previous file.
     */
    public void updateTasks(TaskList tasks) {
        writeTasks(tasks);
        updateRecords();
    }

    public void writeTasks(TaskList tasks) {
        File data = new File(filepath);
        try {
            data.delete();
            data.createNewFile();
            this.fileWriter = new FileWriter(data, false);
            this.printWriter = new PrintWriter(this.fileWriter);
            for (int i = 0; i < tasks.size(); i = i + 1) {
                this.printWriter.println(tasks.get(i).toDataFormat());
                printWriter.flush();
            }
        } catch (IOException e) {
            System.out.println("Error! file not loaded into database.");
        }
    }

    public void updateRecords() {
        TaskList tasks = new TaskList(this.load());
        this.records.push(tasks);
        this.writeTasks(tasks);
    }

    public TaskList undo() {
        if (records.isEmpty()) {
            return new TaskList(this.load());
        }
        records.pop();
        if (records.isEmpty()) {
            return new TaskList(this.load());
        }
        TaskList prev = records.peek();
        writeTasks(prev);
        return prev;
    }
}
