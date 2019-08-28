import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        FileReader fr = new FileReader(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(fr);

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] part = line.split("/");
            String status = part[1];
            boolean isDone;
            if (status.equals("✓")) {
                isDone = true;
            } else {
                isDone = false;
            }
            switch (part[0]) {
            case "T":
                tasks.add(new Todo(part[2], isDone));
                break;
            case "D":
                tasks.add(new Deadline(part[2], isDone, part[3]));
                break;
            case "E":
                tasks.add(new Event(part[2], isDone, part[3]));
                break;
            }
        }
        return tasks;
    }

    public void updateFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getTaskList()) {
            fw.write(task.storageFormat() + "\n");
            fw.flush();
        }
    }
}
