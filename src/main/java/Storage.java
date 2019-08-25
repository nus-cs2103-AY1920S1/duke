import java.util.ArrayList;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        LoadFile lf = new LoadFile(this.getFilePath());
        ArrayList<Task> taskArr = lf.loadTaskFromFile();
        return taskArr;
    }
}
