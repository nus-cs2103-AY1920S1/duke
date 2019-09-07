package tasks;

import customexceptions.DukeException;
import tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage point for data that is input by the user.
 */
public class Storage {
    public File planner;
    public String filePath;

    /**
     * Constructor for Storage object.
     *
     * @param filePath path of the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        planner = new File(filePath);
        setPlannerPermissions(planner);
        ArrayList<Task> taskArrayList = createTaskList(planner);
        return taskArrayList;
        //loading
    }

    /**
     * Create a task list by reading the text file.
     *
     * @param file Text file.
     * @return taskArrayList. Array list of tasks.
     * @throws DukeException if there is an error reading the file.
     */
    public ArrayList<Task> createTaskList(File file) throws DukeException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] taskDetails = sc.nextLine().split("\\|");
                Task task = null;
                switch (taskDetails[0].trim()) {
                    case "T":
                        task = new ToDo(taskDetails[2].trim());
                        setDoneFlag(task, taskDetails[1]);
                        break;
                    case "D":
                        task = new Deadline(taskDetails[2].trim(), taskDetails[3].trim());
                        //System.out.println(task);
                        setDoneFlag(task, taskDetails[1].trim());
                        break;
                    case "E":
                        task = new Event(taskDetails[2].trim(), taskDetails[3].trim());
                        setDoneFlag(task, taskDetails[1].trim());
                        break;
                    default:
                        throw new DukeException("File not in correct format");
                }
                taskArrayList.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not in correct format");
        } finally {
            return taskArrayList;
        }
    }

    /**
     * Sets the tasks to done accordingly
     *
     * @param task Task.
     * @param flag Flag to check if task is done.
     */
    public void setDoneFlag(Task task, String flag) {
        if (flag.equals("+")) {
            task.changeSign();
        }
    }

    /**
     * Giving permissions to edit the file.
     *
     * @param planner Planner.
     */
    private void setPlannerPermissions(File planner) {
        planner.setExecutable(true);
        planner.setReadable(true);
        planner.setWritable(true);
    }

    /**
     * Update the file with tasks from task list.
     *
     * @param taskList task list.
     * @throws DukeException if there is an error with writing on the file.
     */
    public void writeListToFile(TaskList taskList) throws DukeException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> tasks = taskList.getCommandList();
        for (Task task : tasks) {
            sb.append(task);
            sb.append("\n");
        }
        writeStringToFile(sb.toString().trim());
    }

    /**
     * Updates the file with individual strings.
     *
     * @param text String to update the file with.
     * @throws DukeException if there is an error with writing on the file.
     */
    public void writeStringToFile(String text) throws DukeException {
        try {
            FileWriter fw = new FileWriter(planner);
            fw.write(text + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
