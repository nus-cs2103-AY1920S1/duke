import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            // create buffered reader from file
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));

            // load tasks from file
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                tasks.add(loadTask(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private Task loadTask(String line) {
        // use Task class's constructor from String
        Task task = new Task(line);
        return task;
    }

    public void save(ArrayList<Task> tasks) {
        // create json representation and save to given file
    }
}
