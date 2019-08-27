import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

class DukeFileHandler {
    static File dataFile = new File("data/duke.txt");

    static void writeToFile(List<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile);
        for (Task task : taskList) {
            fileWriter.append(task.toString() + "\n");
        }
        fileWriter.close();
    }
}
