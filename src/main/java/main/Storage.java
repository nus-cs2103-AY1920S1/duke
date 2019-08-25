package main;

import java.io.*;
import java.util.ArrayList;
import task.*;

public class Storage {
    private String filepath;
    private File data;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    public Storage(String filepath) {
        this.filepath = filepath;
        this.data = new File(filepath);
    }

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
                    String[] dataReads = dataRead.split(" \\| ");
                    if (dataReads[0].equals("T")) {
                        Task todo = new ToDo(dataReads[2]);
                        tasks.add(todo);
                    } else if (dataReads[0].equals("E")) {
                        Task event = new Event(dataReads[2], dataReads[3], dataReads[4]);
                        tasks.add(event);
                    } else if (dataReads[0].equals("D")) {
                        Task deadline = new Deadline(dataReads[2], dataReads[3]);
                        tasks.add(deadline);
                    }
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

    public void updateTasks(TaskList tasks) {
        try {
            this.data.delete();
            this.data.createNewFile();
            for (int i = 0; i < tasks.size(); i = i + 1) {
                this.printWriter.println(tasks.get(i).toDataFormat());
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
