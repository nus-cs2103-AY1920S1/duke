import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list = new ArrayList<>();
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
    public Task getTask(int i) {
        return this.list.get(i);
    }

    public void save(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (i > 0) {
                writer.newLine();
            }
            writer.write(task.toString());
        }
        writer.close();
    }
}