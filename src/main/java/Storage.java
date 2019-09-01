import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Storage Class which is used to handle all
 * file handling task - writing, deleting, updating.
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
        FileWriter fw = new FileWriter("duke.txt", true);
        fw.write(inputString);
        fw.close();
    }

    /**
     * Updates the existing text file with all the Task which are currently in the list.
     * @param inputTaskList takes in an arraylist (taskList) and updates the text file with all the new task
     * @throws IOException throws exception when user input is invalid
     */
    public void updateTaskToFile(ArrayList<Task> inputTaskList) throws IOException {
        FileWriter fileWriter = new FileWriter("duke.txt", false);
        for (int i = 0; i < inputTaskList.size(); i++) {
            fileWriter.write(inputTaskList.get(i) + System.getProperty("line.separator"));
        }
        fileWriter.close();
    }
}
