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

    public List<Task> loadTasks() throws FileNotFoundException, DukeException {
        List<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        if (f.length() == 0) {
            throw new DukeException();
        }
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] task = input.split(" \\| ");
            switch (task[0]) {
            case "T":
                tasks.add(new ToDo(task[2]));
                break;
            case "D":
                tasks.add(new Deadline(task[2], task[3]));
                break;
            case "E":
                tasks.add(new Event(task[2], task[3]));
                break;
            }
            if (task[1].equals("1")) {
                tasks.get(tasks.size() - 1).setDone();
            }
        }
        return tasks;
    }

    public void saveTasks(TaskList tasks) throws IOException {
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
    }
}
