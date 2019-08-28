package duke.bin.storage;

import duke.bin.DukeException;
import duke.bin.task.Deadline;
import duke.bin.task.Event;
import duke.bin.task.Task;
import duke.bin.task.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataStorage {
    private String filePath;
    private ArrayList<Task> tasks;

    private String newLine = System.getProperty("line.separator");

    /**
     * Public constructor for the storage of the list of tasks.
     * @param filePath file path to save and load in the computer.
     */
    public DataStorage(String filePath) {
        tasks = new ArrayList<Task>();
        this.filePath = filePath;
    }

    public void write(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        boolean isFirst = true;
        for (Task task : taskList) {
            if (isFirst) {
                isFirst = false;
                fw.write(parseTask(task));
            } else {
                fw.write(newLine + parseTask(task));
            }
        }
        fw.close();
    }

    private String parseTask(Task t) {
        return t.toString();
    }

    private Task parseString(String s) throws DukeException {
        String type = s.substring(1, 2);
        String status = s.substring(4, 5);
        String[] spl = s.split(" ", 2);
        Task temp;

        switch(type) {
        case "T":
            temp = new ToDo(spl[1]);
            break;
        case "E":
            String[] nameAndDesc = spl[1].split(" \\(at: ");
            String description = nameAndDesc[1].substring(0, nameAndDesc[1].length() - 1);
            temp = new Event(nameAndDesc[0], description);
            break;
        case "D":
            String[] nameAndDescr = spl[1].split(" \\(by: ");
            String desc = nameAndDescr[1].substring(0, nameAndDescr[1].length() - 1);
            temp = new Deadline(nameAndDescr[0], desc);
            break;
        default:
            temp = null;
            throw new DukeException("Something went wrong");
        }
        return temp;
    }

    public ArrayList<Task> readFromFile() throws IOException, DukeException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(parseString(line));
            }
        } finally {
            br.close();
        }
        return tasks;
    }
}
