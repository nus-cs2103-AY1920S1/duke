import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskWriter {
    private ArrayList<Task> taskEntered;
    final String FILE_NAME = "C:\\Users\\dandf\\Pictures\\CS2103-Duke\\duke/data/duke.txt";
    final String DIRECTORY_NAME = "C:\\Users\\dandf\\Pictures\\CS2103-Duke\\duke/data";
    public TaskWriter(ArrayList<Task> taskEntered){
        this.taskEntered = taskEntered;
    }

    //first checks if data]
    public void writeToFile() {
        try {
            File taskStorage = new File(FILE_NAME);
            File directoryStorage = new File(DIRECTORY_NAME);
            if (!taskStorage.getAbsoluteFile().exists()) {
                directoryStorage.mkdir();
                taskStorage.createNewFile();
            }
            FileWriter taskWrite = new FileWriter(FILE_NAME);
            String s = writeFromArray();
            taskWrite.write(s);
            taskWrite.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String writeFromArray(){
        StringBuffer toWrite = new StringBuffer("");
        for(int i = 0; i < taskEntered.size() ; i++){
            Task t = taskEntered.get(i);
            String s;
            if (t.getType().equalsIgnoreCase("[T]")) {
                s = t.getType() + "|" + t.getIsDone() + "|" + t.getDescription() + "\n";
            } else {
                s = t.getType() + "|" + t.getIsDone() + "|" + t.getDescription()  + "\n";
            }
            toWrite.append(s);
        }
        return toWrite.toString();
    }
}
