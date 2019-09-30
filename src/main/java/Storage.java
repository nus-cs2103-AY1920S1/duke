import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private ArrayList<Task> taskList;

    public Storage(String filePath) {
        this.filePath = filePath;
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> loadFromFile() throws FileNotFoundException {
        readFileContents();

        return taskList;
    }

    public void readFileContents() throws FileNotFoundException{
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            readAsObject(s.nextLine());
        }
    }

    public void writeToFile(TaskList tasks) {
        ArrayList<Task> updatedTaskList = tasks.getTaskList();
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : updatedTaskList) {
                String text = task.getFileFormat();
                fw.write(text + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readAsObject(String item) {
        Task task = new Task("");
        char type = item.charAt(0);
        String[] split = item.split(" \\| ");
        boolean isValidTask = true;

        switch (type) {
            case 'T':
                task = new Todo(split[2]);
                break;
            case 'D':
                task = new Deadline(split[2], new DateTime(split[3]));
                break;
            case 'E':
                task = new Event(split[2], new DateTime(split[3]));
                break;
            default:
                System.out.println("OOPS!!! There is an invalid task in the file!");
                isValidTask = false;
        }

        if (isValidTask) {
            taskList.add(task);
            if (split[1].equals("+")) {
                task.setDone(true);
            }
        }
    }
}
