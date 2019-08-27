import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List loadTasks() throws DukeException { //need to handle exception here
        List<String[]> tasks = new ArrayList<>();
        File f = new File(filePath);
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] arr = task.split(" \\| ");
                tasks.add(arr);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); //CHANGE THIS
        }
        return tasks;
    }

    public void saveTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getList()) {
                if (task instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    fw.write("D | " + d.isDone() + " | " + d.getDescription() + " | " + d.getBy() + "\n");
                } else if (task instanceof Event) {
                    Event e = (Event) task;
                    fw.write("E | " + e.isDone() + " | " + e.getDescription() + " | " + e.getAt() + "\n");
                } else if (task instanceof ToDo) {
                    ToDo t = (ToDo) task;
                    fw.write("T | " + t.isDone() + " | " + t.getDescription() + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace(); // CHANGE THIS
        }
    }
}
