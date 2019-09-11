package duke;

import Exception.DukeException;
import helper.DateTimeHandler;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Storage {

    private String filePath = "";
    private File file;

    /**
     * Creates a storage object.
     * @param filePath refers to the filepath where the txt file is stored.
     * @throws DukeException when the file can't be created.
     */
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new DukeException("I can't find the file path");
            }
        }
    }

    /**
     * Writes a text to the txt file specified in filepath.
     * @param textToAdd refers to the text to be inserted into the txt file.
     * @throws IOException when the text can't be written into the txt file.
     */
    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes a list of tasks to the txt file specified in filepath.
     * @param taskListToAdd a list to be added into the txt file.
     * @throws DukeException when an error occurred with a specific message.
     */
    public void writeToFile(ArrayList<Task> taskListToAdd) throws DukeException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskListToAdd.size(); i++) {
            sb.append(taskListToAdd.get(i).toStringFile() + "\n");
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException ex) {
            throw new DukeException("I could not save your task");
        }
    }

    /**
     * Gets the file content.
     * @return arrayList of tasks
     * @throws DukeException when an error occurred with a specific message.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                Task task = decodeTask(s.nextLine());
                taskList.add(task);
            }
        } catch (Exception ex) {
            throw new DukeException("I can't load your tasks.");
        }
        return taskList;
    }

    /**
     * Reads in the txt file data and create task objects accordingly.
     * @param taskString to parse the string into different tasks.
     * @return {@link Task}
     * @throws DukeException when an error occurred with a specific message.
     */
    public Task decodeTask(String taskString) throws DukeException {
        String[] taskArr = taskString.split(" \\| ");
        Task task;
        switch (taskArr[0]) {
        case "T":
            task = new Todo(taskArr[2]);
            break;
        case "D":
            task = new Deadline(taskArr[2], taskArr[3]);
            break;
        case "E":
            task = new Event(taskArr[2], DateTimeHandler.getDateTimeRange(taskArr[4]), taskArr[4]);
            break;
        default:
            task = new Task();
        }
        task.setIsDone(decodeIsDone(taskArr[1]));
        return task;
    }

    /**
     * Returns true if 1 or else false.
     * @param s string input
     * @return {@link boolean}
     */
    public boolean decodeIsDone(String s) {
        return s.equals("1");
    }
}
