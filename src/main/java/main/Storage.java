package main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import task.Task;
import task.TaskList;
import task.ToDo;
import task.Event;
import task.Deadline;

public class Storage {
    private String filepath;
    private FileWriter fileWriter;
    private PrintWriter printWriter;
    private Stack<TaskList> records;

    /**
     * Constructor for Storage objects.
     * @param filepath String of the location for data storage.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.records = new Stack<>();
    }

    private Task getData(String[] data) {
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
     * @return ArrayList of Task objects is a list that contains the tasks from the previous save point
     *     (if a previous save point exists). Else an empty list is returned.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File data = new File(filepath);
        try {
            if (data.createNewFile()) {
                //If file is already present then use that file.
            } else {
                FileReader fr = new FileReader(data);
                BufferedReader bufferedReader = new BufferedReader(fr);
                while (bufferedReader.ready()) {
                    String dataRead = bufferedReader.readLine();
                    String[] dataReads = dataRead.split(" \\| ");
                    Task t = getData(dataReads);
                    tasks.add(t);
                }
            }
            this.fileWriter = new FileWriter(data, false);
            this.printWriter = new PrintWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * updateTasks updates records and stores the list of tasks in a txt file.
     * @param tasks TaskList. Data in tasks replaces the TaskList data from the previous file.
     */
    public void updateTasks(TaskList tasks) {
        writeTasks(tasks);
        updateRecords();
    }

    /**
     * writeTasks writes tasks in the TaskList to a DukeData.txt file stored in ./ directory.
     * @param tasks TaskList containing tasks to be written in txt file.
     */
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

    /**
     * updateRecords keeps track of history to be used when user invokes undo method.
     */
    public void updateRecords() {
        TaskList tasks = new TaskList(this.load());
        this.records.push(tasks);
        this.writeTasks(tasks);
    }

    /**
     * undo returns the previous state of TaskList one command before modification.
     * @return TaskList containing all tasks before modification.
     */
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
