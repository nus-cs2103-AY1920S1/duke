import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    Scanner sc;
    String filePath;
    List<String> inputs = new ArrayList<>();
    TaskList tasks = new TaskList();
    File f;
    FileWriter fw;

    public Storage (String filePath) {
        this.filePath = filePath;
        f = new File(filePath);
        try {
            if (!f.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public TaskList loadTasks() {
        int counter = 0;
        try {
            sc = new Scanner(f);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] details = task.split(" \\| ");
                int num = Integer.parseInt(details[1]);
                boolean done = false;
                done = (num == 1);
                counter++;
                try {
                    switch (details[0]) {
                        case "T":
                            tasks.loadTask(new Todo(counter, details[2], "T", done));
                            break;
                        case "D":
                            tasks.loadTask(new Deadline(counter, details[2], details[3], "D", done));
                            break;
                        case "E":
                            tasks.loadTask(new Event(counter, details[2], details[3], "E", done));
                            break;
                        default:
                            throw new InvalidInputException();
                    }
                } catch (InvalidInputException e) {
                    e.printError();
                }
            }
        } catch (IOException e) {
            System.out.println("file not detected");
        }
        return tasks;
    }

    public void writeToFile(String text) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public void updateTaskList(TaskList tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.taskList) {
                if (task != null) {
                    fw.write( task.fileFormat() + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
