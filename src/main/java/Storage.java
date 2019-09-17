import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage handler that loads from and saves task to a file location.
 * A <code>Storage</code> object corresponds to a storage handler that interacts
 * with a <code>File</code>.
 */
public class Storage {

    private File diskList;

    /**
     * Constructs a <code>Storage</code> based on a storage filepath.
     *
     * @param filePath Storage filepath.
     */
    public Storage(String filePath) {
        diskList = new File(filePath);
    }

    /**
     * Returns updated list of tasks from specified storage.
     *
     * @return List of tasks.
     * @throws DukeException If diskList does not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s;

        try {
            s = new Scanner(diskList);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not on disk");
        }

        while (s.hasNext()) {
            String[] savedTasks = s.nextLine().split(" \\| ");

            switch (savedTasks[0]) {
            case "T":
                tasks.add(new Todo(savedTasks[1], savedTasks[2]));
                break;
            case "D":
                tasks.add(new Deadline(savedTasks[1], savedTasks[2], savedTasks[3]));
                break;
            case "E":
                tasks.add(new Event(savedTasks[1], savedTasks[2], savedTasks[3]));
                break;
            default:
                assert false : "Saved task should only begin with either T, D or E";
            }
        }

        return tasks;
    }

    /**
     * Overwrites stored list of tasks with given list of tasks.
     *
     * @param tasks List of tasks.
     */
    public void overWrite(ArrayList<Task> tasks) throws IOException {
        diskList.createNewFile();

        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (Task t : tasks) {
            String s = t.formatString();

            if (first) {
                sb.append(s);
                first = false;
            } else {
                sb.append(String.format("\n%s", s));
            }
        }

        FileWriter fw = new FileWriter(diskList);
        fw.write(sb.toString());
        fw.close();
    }
}