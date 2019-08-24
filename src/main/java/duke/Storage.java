package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file path.
     * @return A list of tasks, where each duke.task is represented as a list of strings
     */
    public List<List<String>> load() throws DukeException {
        ArrayList<List<String>> tasks = new ArrayList<>();
        try {
            File file = new File(this.filePath);
            file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> task = Arrays.asList(line.split("\\|"));
                for (int i = 0; i < task.size(); i++) {
                    task.set(i, task.get(i).trim());
                }
                tasks.add(task);
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException("Could not read your tasks!");
        }
        return tasks;
    }

    /**
     * Saves tasks into the file path.
     * @param tasks A list of strings, where each string represents the duke.task to be saved
     */
    public void save(List<String> tasks) throws DukeException {
        try {
            File file = new File(this.filePath);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            boolean first = true;
            for (String task : tasks) {
                writer.write(first ? "" : "\n");
                first = false;
                writer.write(task);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Could not write your tasks!");
        }
    }
}
