import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;

public class SaveToFile {   

    public void updateFile(ArrayList<Tasks> listOfTasks) {
        try {
            FileWriter fw = new FileWriter("/Users/meiannn/Documents/GitHub/duke/src/main/java/TaskList.txt");
            for(Tasks t: listOfTasks) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }

}