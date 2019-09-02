package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Storage {
    private String filePath;

    /**
     * Creates a new Storage instance.
     *
     * @param filePath Relative path of the save file.
     */
    Storage(String filePath) {
        this.filePath = String.format("%s/%s", System.getProperty("user.dir"), filePath);
    }

    /**
     * Loads tasks from a save file into an ArrayList.
     *
     * @return ArrayList with all tasks from the save file.
     * @throws DukeException If file I/O fails
     */
    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));

            String input = reader.readLine();
            while (input != null) {
                Task task = Parser.parseSavedTask(input);
                tasks.add(task);
                input = reader.readLine();
            }
        } catch (IOException e) {
            throw new DukeException("Failed to read save file.");
        }

        return tasks;
    }

    /**
     * Writes all tasks to the save file.
     *
     * @param tasks ArrayList of all tasks.
     * @throws DukeException If file I/O fails
     */
    void persist(ArrayList<Task> tasks) throws DukeException {
        StringBuilder output = new StringBuilder();
        for (Task t : tasks) {
            output.append(String.format("%s%n", t.toSaveFormat()));
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            writer.write(output.toString());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write save file.");
        }

    }
}
