import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriting {
    public static void writeToFile(ArrayList<Task> taskList) throws IOException {
        File tmp = new File("data/temp.txt");
        if(!tmp.createNewFile()){
            Files.delete(Paths.get("data/temp.txt"));
            tmp.createNewFile();
        }

        FileWriter fw = new FileWriter("data/temp.txt",true);
        Iterator<Task> taskIterator = taskList.iterator();
        while (taskIterator.hasNext()) {
            fw.write(taskIterator.next().getDetails() + System.lineSeparator());
        }
        fw.close();
        Files.copy(Paths.get("data/temp.txt"), Paths.get("data/duke.txt"), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get("data/temp.txt"));
    }
}
