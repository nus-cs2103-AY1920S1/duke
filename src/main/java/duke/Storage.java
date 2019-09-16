package duke;

import duke.task.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Encapsulates a file pointer.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath  Path to saved data file on hard disk.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from hard disk.
     *
     * @return Data from hard disk in the form of a List of Tasks.
     * @throws DukeException  If file is not found.
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        String line;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                taskList.add(Task.convertStringToTask(line));
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            throw new DukeException("File not found.\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("IO Exception.\n");
        }
        return taskList;
    }

    /**
     * Updates data in hard disk.
     *
     * @param taskList  Task list with modifications from user interaction via the chat bot.
     * @throws DukeException  If file cannot be updated.
     */
    public void update(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Task task;
            int index = 1;
            for (Iterator iterator = taskList.iterator(); iterator.hasNext(); index++) {
                assert (index >= 1);
                if (index > 1) {
                    bufferedWriter.write("\n");
                }
                task = (Task) iterator.next();
                bufferedWriter.write(task.convertTaskToString());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new DukeException("Failed to update file.\n");
        }
    }
}
