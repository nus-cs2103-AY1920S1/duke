import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private List<Task> tasks = new ArrayList<Task>();
    private String filePath;

    public Storage(String path) {
        this.filePath = path;
    }


    public void writeToFile(String text) throws IOException {

        FileWriter fw = new FileWriter(filePath);// create a FileWriter for the given file path
        fw.write(text);
        fw.close();
    }

    public List<Task> load() throws FileNotFoundException, DukeException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        if (!s.hasNext()) throw new DukeException(
                "Your task list is currently empty.");
        while (s.hasNext()) {
            String str = s.nextLine();
            String[] arr = str.split(" \\| ");
            String taskType = arr[0];
            int taskStatus = Integer.valueOf(arr[1]);
            String taskDes = arr[2];
            String taskTime = "";
            Task task;
            if (taskType.equals("T")) {
                task = new ToDo(taskDes);
                tasks.add(task);
                if (taskStatus == 1) task.mark();
            } else if (taskType.equals("D")) {
                taskTime = arr[3];
                task = new DeadLine(taskDes, taskTime);
                tasks.add(task);
                if (taskStatus == 1) task.mark();
            } else if (taskType.equals("E")) {
                taskTime = arr[3];
                task = new Event(taskDes, taskTime);
                tasks.add(task);
                if (taskStatus == 1) task.mark();
            }
        }

        return tasks;
    }


}
