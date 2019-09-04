package storage;


import ui.TextUi;
import tasklist.Task;
import tasklist.TaskList;
import parser.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Stores and loads files from a user specified file path.
 */

public class Storage {
    private String filePath;
    private TextUi ui;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        ui = new TextUi();
    }

    /**
     * method used to store data by inputting to a file line by line.
     * @param toStore The list of task from a TaskList object containing the tasks to be stored
     * @throws IOException if the filepath specified is invalid
     */

    public void storeData(LinkedList<Task> toStore) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        StringBuilder fullList = new StringBuilder();
        for (Task task : toStore) {
            fullList.append(task.encodeForStorage()).append("\n");
        }
        fileWriter.write(fullList.toString());
        fileWriter.close();
    }

    /**
     * method used to read files and load data on to linkedlist to be passed to TaskList object.
     * @return returns a linkedList to a TaskList object with all the tasks already loaded
     * @throws IOException if the the file path is invalid
     */

    public LinkedList<Task> loadData() throws IOException {
        FileReader reader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(reader);
        TaskList storedData = new TaskList();
        Parser parser = new Parser();
        String line;
        int tasknumber = 1;

        while ((line = bufferedReader.readLine()) != null) {
            parser.parse(line,storedData,true);
            if (!parser.isSafe()) {
                ui.printLoadingError(tasknumber);
            }
            tasknumber++;
        }
        bufferedReader.close();
        return storedData.getTaskList();
    }

}


