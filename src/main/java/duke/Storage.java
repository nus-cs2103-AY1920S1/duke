package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DateTimeParseDukeException;
import duke.task.Task;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads and the text in the specified file. Parses the String to store
     * as different types of tasks
     * @return ArrayList of tasks
     * @throws IOException if file cannot be opened
     * @throws DateTimeParseDukeException if date time of string is in invalid format
     */
    public ArrayList<Task> load() throws IOException, DateTimeParseDukeException {
        ArrayList<Task> taskList = new ArrayList<Task>();

        Path path = Paths.get(filePath);
        List<String> allLines = Files.readAllLines(path);

        for (String temp: allLines) {
            taskList.add(Task.parseFileTask(temp));
        }

        return taskList;
    }

    /**
     * Updates save file with current list of tasks.
     * @param tasks duke.Tasklist
     */
    public void updateSaveFile(TaskList tasks) {
        String temp = tasks.generateListForFile();
        this.writeStringToFile(temp);
    }

    /**
     * Writes input string to specified file location.
     * @param temp String to be saved into file.
     */
    private void writeStringToFile(String temp) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            fileWriter.write(temp);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}