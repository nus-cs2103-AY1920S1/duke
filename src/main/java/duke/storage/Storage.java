package duke.storage;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.exception.DukeException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

/**
 * Performs actions on the save data file on hard disk.
 */
public class Storage {
    private String filePath;

    private ArrayList<Task> list;

    /**
     * Constructs an instance of Storage based on the location of the save data file.
     *
     * @param fp path of the save data file.
     */
    public Storage(String fp) {
        this.filePath = fp;
    }

    /**
     * Loads the list of tasks stored on hard disk.
     *
     * @return an ArrayList to be stored in an instance of Storage.
     * @throws DukeException error occurred during the reading of saved tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        list = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String item = sc.nextLine();
                String type = item.substring(0, 3);
                String status = item.substring(3, 6);
                String desc;
                String date = "";
                if (item.contains("(")) {
                    int dateDescInd = item.indexOf("(");
                    desc = item.substring(7, dateDescInd - 1);
                    date = item.substring(dateDescInd + 5, item.length() - 1);
                } else {
                    desc = item.substring(7);
                }
                Task t = null;
                switch (type) {
                case "[D]" :
                    t = new Deadline(desc, date);
                    break;
                case "[T]" :
                    t = new ToDo(desc);
                    break;
                case "[E]" :
                    t = new Event(desc, date);
                    break;
                default:
                    System.out.println("Unknown event type encountered.");
                }
                if (status.equals("[✓]")) {
                    t.markAsDone();
                }
                list.add(t);
            }
            System.out.println("Loaded save data file successfully.");
        } catch (FileNotFoundException e) {
            //Create a new file if no file found
            File f = new File(filePath);
            try {
                if (f.createNewFile()) {
                    System.out.println("No existing file found! New save data file created!");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException ex) {
                throw new DukeException("Data file creation failed.");
            }
        }
        return list;
    }

    /**
     * Saves and updates the list of tasks.
     *
     * @param tl an instance of TaskList object.
     * @throws DukeException error occurred during the saving of new tasks.
     */
    public void save(TaskList tl) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t: tl.getTaskList()) {
                fw.write(t.toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException ex) {
            throw new DukeException("Data file saving failed.");
        }
    }
}
