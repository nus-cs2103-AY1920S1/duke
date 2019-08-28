import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class DukeFileWriter {
    public static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String allTaskData = "";
        for (Task x: tasks) {
            allTaskData += x.toFileFormat();
            allTaskData += "\n";
        }
        fw.write(allTaskData);
        fw.close();
    }
}
