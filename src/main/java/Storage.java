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

    public void writeToFile(String inputString) throws IOException {
        FileWriter fw = new FileWriter("D:\\NUS Computer Science\\CS2103T\\duke\\src\\main\\java\\duke.txt", true);
        fw.write(inputString);
        fw.close();
    }

    public void updateTaskToFile(ArrayList<Task> inputTaskList) throws IOException {
        FileWriter fileWriter = new FileWriter("D:\\NUS Computer Science\\CS2103T\\duke\\src\\main\\java\\duke.txt", false);
        for (int i = 0; i < inputTaskList.size(); i++) {
            fileWriter.write(inputTaskList.get(i) + System.getProperty("line.separator"));
        }
        fileWriter.close();
    }
}
