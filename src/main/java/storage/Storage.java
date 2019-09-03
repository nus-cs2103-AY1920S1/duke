package storage;


import tasklist.Task;
import tasklist.TaskList;
import parser.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Storage {
    protected String filePath;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
    }

    public void storeData(LinkedList<Task> toStore) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        String fullList = "";
        for (Task task : toStore) {
            fullList = fullList + task.encodeForStorage() + "\n";
        }
        fileWriter.write(fullList);
        fileWriter.close();
    }

    public LinkedList<Task> loadData() throws IOException {
        FileReader reader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(reader);
        TaskList storedData = new TaskList();
        Parser parser = new Parser();
        String line;
        int tasknumber = 1;

        while ((line = bufferedReader.readLine()) != null) {
            parser.parse(line,storedData);
            if (!parser.isSafe()) {
                System.out.println("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but loading task " + tasknumber +
                        " has failed, it will be removed\n" +
                        "    ____________________________________________________________");
            }
            tasknumber++;
        }
            bufferedReader.close();
            return storedData.getTaskList();
    }

}


