import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

/**
 * Represents a File Writer that is responsible for saving changes to the Duke App.
 */
public class DukeFileWriter {
    /**
     * Saves the task list to a save file.
     *
     * @param filePath the filepath to write the file.
     * @param tasks    the tasks to be stored
     * @throws IOException throws IOException when there is an error in the IO process.
     */
    public static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        assert !filePath.equals("") : "filePath should not be empty";
        FileWriter fw = new FileWriter(filePath);
        String allTaskData = "";
        for (Task x : tasks) {
            allTaskData += x.toFileFormat();
            allTaskData += "\n";
        }
        fw.write(allTaskData);
        fw.close();
    }
}
