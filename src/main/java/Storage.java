import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;
import

public class Storage {

    private File data;
    private FileWriter fileWriter;

    public Storage() throws IOException {
        data = new File("tasklist.txt");
        fileWriter = new FileWriter(data);
    }
    public Storage(String filePath) throws IOException {
        data = new File(filePath);
        fileWriter = new FileWriter(data);
    }

    public void StoreData(LinkedList<Task> toStore) {
        try {
            for (Task task : toStore) {
                fileWriter.write(task.encodeForStorage());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void TaskList LoadData() throws FileNotFoundException {
        Scanner reader = new Scanner(data);
        while (reader.hasNext()){
            pars
        }


    }
}

