package duke;

import duke.task.Task;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        String line;
        try {
            FileReader fileReader = new FileReader(this.filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                taskList.add(Task.convertStringToTask(line));
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            throw new DukeException("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public void update(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Task task;
            int index = 1;
            for (Iterator iterator = taskList.iterator(); iterator.hasNext(); index++) {
                if (index > 1)
                    bufferedWriter.write("\n");
                task = (Task) iterator.next();
                bufferedWriter.write(task.convertTaskToString());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new DukeException("Failed to update file.\n");
        }
    }
}
