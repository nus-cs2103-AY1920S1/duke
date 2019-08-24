import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }

    public List<Task> loadTasks() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<Task> taskList = new ArrayList<>();
        while (scanner.hasNext()) {
            taskList.add(Task.from(scanner.nextLine()));
        }
        return taskList;
    }

    public void recordTasks(TaskList taskList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        String content = "";

        for (int i = 0; i < taskList.getTotalTask(); i++) {
            Task task = taskList.getTaskAt(i);
            if (task instanceof Todo) {
                content = "T|" + task.getStatus() +  "|" + task.getDescription();
            } else if (task instanceof Deadline) {
                Deadline dl = (Deadline) task;
                content = "D|" + task.getStatus() +  "|" + dl.getDescription() + "|" + dl.getDueDateTime();
            } else if (task instanceof Event) {
                Event e = (Event) task;
                content = "E|" + e.getStatus() + "|" + e.getDescription() + "|" + e.getStartDateTime() + "|"
                        + e.getEndTime();
            }
            writer.append(content + "\n");
        }

        writer.close();
    }
}
