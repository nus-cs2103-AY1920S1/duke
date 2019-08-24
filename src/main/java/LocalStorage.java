import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LocalStorage {
    private File tasksFile;

    public LocalStorage(String filePath) {
        this.tasksFile = new File(filePath);
    }

    public void writeToTasksFile(TaskList tasks) {
        List<Task> taskList = tasks.getList();
        try {
            FileWriter fw = new FileWriter(tasksFile);
            for (Task t : taskList) {
                String fileString = t.convertTaskToFileString() + "\n";
                fw.write(fileString);
            }
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }


}
