package puke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles reading and writing tasks from and to disk.
 */
public class Storage {
    private File taskFile;

    /**
     * Creates a new Storage anchored to a specific file.
     *
     * @param path The file's pathname.
     */
    public Storage(String path) {
        this.taskFile = new File(path);
    }

    /**
     * Reads tasks from this instance's task file, one task per line.
     *
     * @return An ArrayList of strings, each string representing a task.
     * @throws FileNotFoundException If the file does not exist or is otherwise inaccessible.
     */
    public ArrayList<String> read() throws FileNotFoundException {
        ArrayList<String> out = new ArrayList<>();
        Scanner sc = new Scanner(taskFile);
        while (sc.hasNextLine()) {
            out.add(sc.nextLine());
        }
        return out;
    }

    /**
     * Writes the given string representations of tasks to this instance's task file.
     *
     * @param lines The lines to be written, produced by TaskList's export() method.
     * @throws IOException If an error occurs while writing the tasks.
     */
    public void write(ArrayList<String> lines) throws IOException {
        FileWriter writer = new FileWriter(taskFile);
        for (String line: lines) {
            writer.write(line + "\n");
        }
        writer.close();
    }
}
