package duke;

import java.io.*;
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

    public ArrayList<Task> load() throws IOException, DateTimeParseDukeException {
        ArrayList<Task> taskList = new ArrayList<Task>();

        Path path = Paths.get(filePath);
        List<String> allLines = Files.readAllLines(path);

        for (String temp: allLines) {
            taskList.add(Task.parseFileTask(temp));
        }

        return taskList;
    }

    public void updateSaveFile(TaskList tasks) {
        String temp = tasks.generateListForFile();
        this.writeStringToFile(temp);
    }

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