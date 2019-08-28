import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class FileHandler {
    
    public static void saveLocationAndName (LinkedList<Task> tasks, String saveLocation, String fileName) throws DukeException {
        
        String strToAdd;
        String filePath = saveLocation + "/" + fileName;
        //Future: Check if filePath is valid?

        try {
            //Check if dir exists, creates if dir does not exist.
            File dataDir = new File(saveLocation);
            if (!dataDir.exists()) { new File("../data").mkdirs(); }
            //Future: Check if parent dir exists?

            //Check if file exists, creates if file does not exist.
            File saveFile =  new File(saveLocation + "/" + fileName);
            if (!saveFile.exists()) { saveFile.createNewFile(); }
            
            //Initialise FileWriter
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) { 
                strToAdd = task.toSaveString();
                fw.write(strToAdd);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Save Failed: " + e.getMessage());
        }
    }
}
