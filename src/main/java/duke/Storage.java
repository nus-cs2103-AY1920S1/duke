package duke;

import duke.tasks.Task;
import duke.exceptions.DukeException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Represents a Storage Class which is used to handle all
 * file handling task - writing, deleting, updating of a text file.
 */

public class Storage {

    private File taskFile;
    private String filePath;

    public Storage(String filePath) {
        this.taskFile = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Writes a user input String into a text file.
     * @param inputString Should be the string format of the input task by users
     * @throws IOException returns exception if invalid input is entered
     */
    public void writeToFile(String inputString) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(inputString);
        fw.close();
    }

    /**
     * Updates the existing text file with all the Task which are currently in the list.
     * @param inputTaskList takes in an arraylist (taskList) and updates the text file with all the new task
     * @throws IOException throws exception when user input is invalid
     */
    public void updateTaskToFile(ArrayList<Task> inputTaskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, false);
        for (int i = 0; i < inputTaskList.size(); i++) {
            fileWriter.write(inputTaskList.get(i) + System.getProperty("line.separator"));
        }
        fileWriter.close();
    }

    /**
     * Reads an existing textFile line by line, and process them to create Task Objects to store.
     * @return a ArrayList consisting of Task objects that corresponds to the textFile commands
     * @throws DukeException for invalid input
     * @throws IOException for invalid I/O
     * @throws ParseException for invalid Input
     */
    public ArrayList<Task> loadFromTextFile() throws DukeException, IOException, ParseException {
        ArrayList<Task> taskList = new ArrayList<>();
        System.out.println("Loaded");
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            taskList.add(Parser.parseFileInput(line));
            line = reader.readLine();
        }
        reader.close();
        return taskList;
    }
}
