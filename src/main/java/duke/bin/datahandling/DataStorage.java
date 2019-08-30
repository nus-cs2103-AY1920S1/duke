package duke.bin.datahandling;

import duke.bin.common.DukeException;
import duke.bin.common.Time;
import duke.bin.task.Deadline;
import duke.bin.task.Event;
import duke.bin.task.Task;
import duke.bin.task.ToDo;

import java.io.*;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class DataStorage {
    private String filePath;
    private ArrayList<Task> tasks;

    private final String newLine = System.getProperty("line.separator");

    /**
     * Public constructor for the storage of the list of tasks.
     * @param filePath file path to save and load in the computer.
     */
    public DataStorage(String filePath) {
        tasks = new ArrayList<Task>();
        this.filePath = filePath;
    }

    /**
     * Writes the list of task into the save file.
     *
     * @param taskList the list to be saved.
     * @throws DukeException thrown if file cannot be found.
     */
    public void write(ArrayList<Task> taskList) throws DukeException {
        try {
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
        } catch (IOException e) {
            throw new DukeException("Oops something when wrong with saving the data.");
        }
    }

    private String parseTask(Task t) {
        return t.toString();
    }

    private Task parseString(String s) throws DukeException {
        try {
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
                String time = nameAndDesc[1].substring(0, nameAndDesc[1].length() - 1);
                Time t = new Time(time);
                temp = new Event(nameAndDesc[0], t);
                break;
            case "D":
                String[] nameAndDescr = spl[1].split(" \\(by: ");
                String desc = nameAndDescr[1].substring(0, nameAndDescr[1].length() - 1);
                Time t2 = new Time(desc);
                temp = new Deadline(nameAndDescr[0], t2);
                break;
            default:
                temp = null;
                throw new DukeException("Something went wrong with the save file.");
            }
            return temp;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops something when wrong with the save file.");
        }
    }

    /**
     * Loads the list of tasks from the save file.
     *
     * @return the list of task from the save file.
     * @throws DukeException if the file cannot be found or parsed correctly.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            readFromFile();
        } catch (IOException e) {
            throw new DukeException("Oops something when wrong with loading your tasks. So sorry.");
        }
        return tasks;
    }

    private void readFromFile() throws IOException, DukeException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(parseString(line));
            }
        } finally {
            br.close();
        }
    }
}
