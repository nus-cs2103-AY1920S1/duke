package duke.lib.datahandling;

import duke.lib.common.DukeException;
import duke.lib.common.Time;
import duke.lib.task.Deadline;
import duke.lib.task.Event;
import duke.lib.task.Task;
import duke.lib.task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class DataStorage {
    final static String FILE_PATH = "/data/save.txt";
    private ArrayList<Task> tasks;

    private final String newLine = System.getProperty("line.separator");

    /**
     * Public constructor for the storage of the list of tasks.
     */
    public DataStorage() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Writes the list of task into the save file.
     *
     * @param taskList the list to be saved.
     * @throws DukeException thrown if file cannot be found.
     */
    public void write(ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
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
            assert !s.isEmpty();
            String type = s.substring(1, 2);
            String status = s.substring(4, 5);
            String[] spl = s.split(" ", 2);
            Task temp;

            switch (type) {
            case "T": {
                temp = new ToDo(spl[1]);
                break;
            }
            case "E": {
                String[] nameAndDesc = spl[1].split(" \\(at: ");
                String time = nameAndDesc[1].substring(0, nameAndDesc[1].length() - 1);
                Time t = new Time(time);
                temp = new Event(nameAndDesc[0], t);
                break;
            }
            case "D": {
                String[] nameAndDesc = spl[1].split(" \\(by: ");
                String desc = nameAndDesc[1].substring(0, nameAndDesc[1].length() - 1);
                Time t = new Time(desc);
                temp = new Deadline(nameAndDesc[0], t);
                break;
            }
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
//            throw new DukeException("Oops something when wrong with loading your tasks. So sorry.");
            throw new DukeException(e.getMessage() + " " + e.toString());
        }
        return tasks;
    }

    private void readFromFile() throws IOException, DukeException {
//        Charset utf8 = StandardCharsets.UTF_8;
//        Path path = Paths.get(FILE_PATH);
//        Path parentDir = path.getParent();
//        Files.createDirectories(parentDir);
//        Files.write(path, Collections.singleton(""), utf8);
//        File file = new File(FILE_PATH);
        File parentDir = new File("/data");
        if (!parentDir.exists()) {
            parentDir.mkdir();
        }
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedReader br = new BufferedReader(new FileReader(file));

        try {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    tasks.add(parseString(line));
                }
            }
        } finally {
            br.close();
        }
    }
}
