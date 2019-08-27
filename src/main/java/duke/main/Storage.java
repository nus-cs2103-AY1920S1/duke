package duke.main;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents an interface for interaction with files. A <code>Storage</code> object
 * is able to read and write to files that are specified.
 */
public class Storage {
    private String filePath;
    private File planner;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        planner = new File(filePath);
        setPlannerPermissions(planner);
        ArrayList<Task> tasksList = createTasksList(planner);
        return tasksList;
    }

    /**
     * Returns an ArrayList of tasks in the specified file.
     * Throws DukeException when error occurs parsing the file or in incorrect format.
     *
     * @param file  File object containing string representation of Task objects.
     * @throws DukeException  If error occurs while parsing the file.
     */
    public ArrayList<Task> createTasksList(File file) throws DukeException {
        ArrayList<Task> tasksList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                String[] taskDetails = sc.nextLine().split("\\|");
                Task task;
                switch(taskDetails[0].trim()) {
                case "T":
                    task = new ToDo(taskDetails[2].trim());
                    setDoneFlag(task, taskDetails[1].trim());
                    break;
                case "D":
                    task = new Deadline(taskDetails[2].trim(), taskDetails[3].trim());
                    setDoneFlag(task, taskDetails[1].trim());
                    break;
                case "E":
                    task = new Event(taskDetails[2].trim(), taskDetails[3].trim());
                    setDoneFlag(task, taskDetails[1].trim());
                    break;
                default:
                    throw new DukeException("Storage File not in correct format");
                }
                tasksList.add(task);
            }
        }
        catch (FileNotFoundException ex) {
            throw new DukeException(ex.getMessage());
        }
        finally {
            return tasksList;
        }
    }

    public void setDoneFlag(Task task, String flag) {
        if (flag.equals("+")) {
            task.markAsDone();
        }
    }

    private void setPlannerPermissions(File planner) {
        planner.setExecutable(true);
        planner.setReadable(true);
        planner.setWritable(true);
    }

    /**
     * Overwrites specified text to a file, throws DukeException if error occurs.
     *
     * @param textToAdd  String of text to write to file.
     * @throws DukeException  If error occurs while reading and writing to file.
     */
    public void writeStringToFile(String textToAdd) throws DukeException {
        try {
            FileWriter fw = new FileWriter(planner); //creates FileWriter in overwrite mode
            fw.write(textToAdd + "\n");
            fw.close();
        }
        catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }

    /**
     * Overwrites file with String representation of a tasks in TaskList object,
     * throws DukeException if error occurs.
     *
     * @param taskList  List of tasks to write to file.
     * @throws DukeException  If error occurs while reading and writing to file.
     */
    public void writeListToFile(TaskList taskList) throws DukeException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> tasks = taskList.getTasksList();
        for (Task task : tasks) {
            sb.append(task);
            sb.append("\n");
        }
        writeStringToFile(sb.toString().trim());
    }

    /**
     * Adds to text of a file, throws DukeException if error occurs.
     *
     * @param task  Task to add to the file.
     * @throws DukeException  If error occurs while reading and writing to file.
     */
    public void addTaskToFile(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(planner, true); // create a FileWriter in append mode
            fw.write(task + "\n");
            fw.close();
        }
        catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }
}