package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.task.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles storage related functions, e.g. loads data from disk and saves data to disk.
 */
public class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data in the file path and returns the corresponding list of tasks.
     * @return The list of tasks.
     */
    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            String data = Files.readString(Path.of(filePath));
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                list.add(Task.parse(sc.nextLine()));
            }
            return list;
        } catch (IOException e) {
            // no such file
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
        return new ArrayList<>();
    }

    /**
     * Saves the list to the current directory.
     * @param list The list to be saved.
     */
    public void save(List<Task> list) {
        StringBuilder sb = new StringBuilder();
        for (Task task : list) {
            sb.append(task.getStringRepresentation());
            sb.append("\n");
        }
        String text = sb.toString();
        BufferedWriter output = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                boolean created = file.createNewFile();
            }
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
