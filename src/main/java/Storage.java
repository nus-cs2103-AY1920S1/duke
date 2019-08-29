import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage {

    private String filePath;

    public Storage(String filePath) throws FileNotFoundException, IOException {
        setFilePath(filePath);
    }

    public void setFilePath(String filePath) throws FileNotFoundException, IOException {
        this.filePath = filePath;
    }

    public void writeTasks(String content) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(content);
        fw.close();
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException("Please check your file path!");
        }
        Scanner scanner = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (scanner.hasNext()) {
            try {
                String line = scanner.nextLine();
                tasks.add(parseStoredTask(line));
            } catch (DukeException e) {
                // What to do?
            }
        }
        scanner.close();
        return tasks;
    }

    private Task parseStoredTask(String task) throws DukeException {
        String[] tokens = task.substring(3).split("\\s\\|\\s");
        if (tokens[0].equals("D")) {
            Deadline d = new Deadline(tokens[2], tokens[3]);
            if (tokens[1].equals("1")) {
                d.setDone(true);
            }
            return d;
        } else if (tokens[0].equals("E")) {
            Event e = new Event(tokens[2], tokens[3]);
            if (tokens[1].equals("1")) {
                e.setDone(true);
            }
            return e;
        } else if (tokens[0].equals("T")) {
            Todo t = new Todo(tokens[2]);
            if (tokens[1].equals("1")) {
                t.setDone(true);
            }
            return t;
        } else {
            throw new DukeException("OOPS!!! Invalid task.");
        }
    }
}
