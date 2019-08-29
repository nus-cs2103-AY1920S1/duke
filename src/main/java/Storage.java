import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String pathToFile;
    private File tasks;

    public Storage(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public List<Task> load() throws IOException {
        File f = new File(pathToFile);
        f.createNewFile();
        this.tasks = f;
        Path p = Paths.get(pathToFile);
        List<String> lst = Files.readAllLines(p);
        List<Task> taskList = new ArrayList<>();
        for (String t : lst) {
            taskList.add(Task.toTask(t));
        }
        return taskList;
    }

    public void save(List<Task> lst) throws IOException {
        this.tasks = new File(pathToFile);
        if (this.tasks.exists()) {
            this.tasks.delete();
        }
        this.tasks.createNewFile();
        FileWriter fw = new FileWriter(tasks, true);
        Path p = Paths.get(pathToFile);
        for (Task tsk : lst) {
            fw.write(tsk.toString());
        }
        fw.close();
    }
}
