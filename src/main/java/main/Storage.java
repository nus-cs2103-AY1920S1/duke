package main;

import java.io.*;
import java.util.ArrayList;
import task.*;

public class Storage {
    private String filepath;
    private File data;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    /**
     * Constructor for Storage objects
     * @param filepath String of the location for data storage.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.data = new File(filepath);
    }

    /**
     * Reads tasks from a previous saved file (if present) and store it in a list of tasks.
     * @return ArrayList<Task> a list that contains the tasks from the previous save point
     * (if a previous save point exists). Else an empty list is returned.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            if (data.createNewFile()) {
                System.out.println("    Previous file not found. Creating a new file");
            } else {
                FileReader fr = new FileReader(data);
                BufferedReader bufferedReader = new BufferedReader(fr);
                while (bufferedReader.ready()) {
                    String dataRead = bufferedReader.readLine();
                    Task t = null;
                    String[] dataReads = dataRead.split(" \\| ");
                    if (dataReads[0].equals("T")) {
                        t = new ToDo(dataReads[2]);
                    } else if (dataReads[0].equals("E")) {
                        t = new Event(dataReads[2], dataReads[3], dataReads[4]);
                    } else if (dataReads[0].equals("D")) {
                        t = new Deadline(dataReads[2], dataReads[3]);
                    }

                    if (dataReads[1].equals("+")) {
                        Task.markAsDone(t);
                    }
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
        try {
            this.data.delete();
            this.data.createNewFile();
            this.fileWriter = new FileWriter(this.data, false);
            this.printWriter = new PrintWriter(this.fileWriter);
            for (int i = 0; i < tasks.size(); i = i + 1) {
                this.printWriter.println(tasks.get(i).toDataFormat());
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
