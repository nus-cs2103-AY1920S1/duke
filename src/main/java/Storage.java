import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

/**
 * Represents a storage class which is responsible for saving and loading to and from the save file.
 */
public class Storage {
    String filePath;

    /**
     * Constructs a Storage Object to handle saving or loading.
     *
     * @param filePath the location of the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        //System.out.println("full path: " + file.getAbsolutePath());
        if (!file.exists()) {
            try {
                new File("data").mkdir();
                file.createNewFile();
                //ui.showMessage("created new save file");
            } catch (IOException err) {
                throw new FileErrorDukeException(filePath);
            }
        } else {
            //ui.showMessage("Save file Detected");
        }
    }

    /**
     * Returns an arrayList that has all currently stored tasks.
     *
     * @return an ArrayList containing all the tasks stored.
     */
    public ArrayList<Task> getData() {
        try {
            return DukeFileReader.getData(filePath);
        } catch (IOException err) {
            throw new FileErrorDukeException(filePath);
        }
    }

    /**
     * Writes the given TaskList to the save file.
     *
     * @param tasks The Tasklist that has all the tasks to be saved.
     */
    public void writeList(TaskList tasks) {
        try {
            DukeFileWriter.writeToFile(this.filePath, tasks.getList());
        } catch (IOException err) {
            throw new FileErrorDukeException(filePath);
        }
    }
}
