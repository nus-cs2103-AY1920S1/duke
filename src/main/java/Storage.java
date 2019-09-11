import java.io.File;
import java.io.FileWriter;

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
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner s = new Scanner(diskList);

            while (s.hasNext()) {
                String[] savedTasks = s.nextLine().split(" \\| ");

                switch (savedTasks[0]) {
                case "T":
                    Todo t = new Todo(savedTasks[2]);
                    if (savedTasks[1].equals("1")) {
                        t.setDone();
                    }
                    tasks.add(t);
                    break;
                case "D":
                    Deadline d = new Deadline(savedTasks[2], savedTasks[3]);
                    if (savedTasks[1].equals("1")) {
                        d.setDone();
                    }
                    tasks.add(d);
                    break;
                case "E":
                    Event e = new Event(savedTasks[2], savedTasks[3]);
                    if (savedTasks[1].equals("1")) {
                        e.setDone();
                    }
                    tasks.add(e);
                    break;
                default:
                    assert false : "Saved task should only begin with either T, D or E";
                }
            }
        } catch (Exception e) {
            throw new DukeException();
        }

        return tasks;
    }

    /**
     * Overwrites stored list of tasks with given list of tasks.
     *
     * @param tasks List of tasks.
     */
    public void overWrite(ArrayList<Task> tasks) {
        try {
            diskList.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        try {
            FileWriter fw = new FileWriter(diskList);
            fw.write(sb.toString());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}