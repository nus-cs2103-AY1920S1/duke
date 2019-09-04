import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;

public class SaveToFile {
    String filePath;

    public SaveToFile(String filePath) {
        this.filePath = filePath;
    }
    public void updateFile(ArrayList<Tasks> listOfTasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for(Tasks t: listOfTasks) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }

}