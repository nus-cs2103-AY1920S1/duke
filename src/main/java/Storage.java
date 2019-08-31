import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> load() throws DukeException, FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();

        File f = new File(filePath);
        if (!f.exists()) {
            throw new DukeException("File not found.");
        }

        Scanner readFileScanner = new Scanner(f);
        while(readFileScanner.hasNext()) {
            String[] todoTask = readFileScanner.nextLine().split(" \\| ");
            switch (todoTask[0]) {
                case "T":
                    Task task1 = new Todo(todoTask[2]);
                    if (todoTask[1].equals("1")) {
                        task1.markAsDone();
                    }
                    tasks.add(task1);
                    break;
                case "D":
                    Task task2 = new Deadline(todoTask[2], todoTask[3]);
                    if (todoTask[1].equals("1")) {
                        task2.markAsDone();
                    }
                    tasks.add(task2);
                    break;
                case "E":
                    Task task3 = new Event(todoTask[2], todoTask[3]);
                    if (todoTask[1].equals("1")) {
                        task3.markAsDone();
                    }
                    tasks.add(task3);
                    break;
                default:
                    throw new DukeException("Something in file go wrong.");
            }
        }
        readFileScanner.close();

        return tasks;
    }

    void saveFile(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String data = tasks.get(0).toFile();
            if (tasks.size() > 1) {
                for (int i = 1; i < tasks.size(); i++) {
                    data = data + System.lineSeparator() + tasks.get(i).toFile();
                }
            }
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("No such file.");
        }
    }
}
