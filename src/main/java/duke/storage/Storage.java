package duke.storage;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading and saving of tasks from the hard-disk.
 */
public class Storage {

    private String filepath;

    /**
     * Constructor for the storage object.
     */
    public Storage() {
        filepath = Paths.get(".").toAbsolutePath().getParent()
                .toString() + "\\data\\tasks.txt";
    }

    /**
     * Loads tasks from the hard disk.
     * @return Loaded tasks in the form of a string list.
     * @throws FileNotFoundException If the file on the hard-disk doesn't exist.
     */
    public ArrayList<String> loadTasks() throws FileNotFoundException {
        File f = new File(filepath);
        ArrayList<String> tasks = loadFromFile(f);
        return tasks;
    }

    private ArrayList<String> loadFromFile(File f) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        ArrayList<String> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            tasks.add(sc.nextLine());
        }
        sc.close();
        return tasks;
    }

    /**
     * Saves the list of tasks into a file on the hard-disk.
     * @param tasks The list of tasks.
     * @throws IOException If the file doesn't exist.
     */
    public void saveTasks(ArrayList<String> tasks) throws IOException {
        File f = new File(filepath);
        FileWriter writer = new FileWriter(f);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            writeToFile(writer, tasks);
        } finally {
            writer.close();
        }
    }

    private void writeToFile(FileWriter writer, ArrayList<String> tasks) throws IOException {
        for (String s : tasks) {
            writer.write(s + "\n");
        }
    }

    /**
     * Deletes the existing file on the hard disk and creates a new copy.
     * @throws DukeException If the file is not found.
     */
    public void flushData() throws DukeException {
        try {
            File f = new File(filepath);
            f.delete();
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeException("File not found. The directory entered is invalid");
        }
    }
}
