import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> getData() {
        try {
            return DukeFileReader.getData(filePath);
        } catch (IOException err) {
            throw new FileErrorDukeException(filePath);
        }
    }

    public void writeList(TaskList tasks) {
        try {
            DukeFileWriter.writeToFile(this.filePath, tasks.getList());
        } catch (IOException err){
            throw new FileErrorDukeException(filePath);
        }
    }
}
