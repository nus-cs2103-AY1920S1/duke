package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;

/**
 * Class that represents the files in the hard drive to be modified or accessed.
 */
public class Storage {

    /**
     * Reader needed to read the file in hard drive.
     */
    private BufferedReader br;

    /**
     * Used to access the file in the hard drive with given filepath.
     */
    private FileReader fr;

    private File file;

    /**
     * Main list of tasks of the program to be written into or accessed from hard drive.
     */
    private ArrayList<Task> inputList;

    /**
     * Filepath of the file in the hard drive.
     */
    private String filepath;

    /**
     * Constructor that takes in the filepath of the data.
     * @param path The filepath of the data as String.
     * @throws Exception Used to handle exception that occurs.
     */
    public Storage(String path) throws Exception {
        this.filepath = path;
        file = new File(filepath);
        file.getParentFile().mkdirs();
        this.inputList = new ArrayList<Task>();
        try {
            this.fr = new FileReader(file);
        } catch (FileNotFoundException error) {
            writeToFile();
            this.fr = new FileReader(file);
        }
        this.br = new BufferedReader(fr);
    }

    /**
     * Used to get the list of tasks.
     * @return The main list of tasks as ArrayList.
     */
    public ArrayList<Task> getList() {
        return this.inputList;
    }

    /**
     * Used to update the main task list every time there is a change.
     * @param updatedList The new list to replace the old one.
     */
    public void updateTaskList(ArrayList<Task> updatedList) {
        this.inputList = updatedList;
    }

    /**
     * Used to load the list of tasks from hard drive into the program to be modified.
     * @throws Exception Used to handle exception that occurs when the method is running.
     */
    public void loadTasks() throws Exception {
        String next = br.readLine();
        while (next != null) {
            String[] input = next.split(" ");
            String status = input[1];
            String type = input[0];
            String description = "";
            String extraInfo = "";
            for (int i = 2; i < input.length; i++) {
                if (i == input.length - 1) {
                    description += input[i];
                } else {
                    description += input[i];
                    description += " ";
                }
            }
            if (type.equals("todo")) {
                inputList.add(new Todo(description));
            } else if (type.equals("event")) {
                extraInfo = br.readLine();
                inputList.add(new Event(description, extraInfo));
            } else if (type.equals("deadline")) {
                extraInfo = br.readLine();
                inputList.add(new Deadline(description, extraInfo));
            }
            if (status.equals("done")) {
                inputList.get(inputList.size() - 1).completeTask();

            }
            next = br.readLine();
        }
    }

    /**
     * Used to update the hard drive with the most current list of tasks.
     * @throws Exception Used to handle any exception that occurs.
     */
    public void writeToFile() throws Exception {
        FileWriter fw = new FileWriter(file);
        for (Task task : inputList) {
            String output = "";
            String status = "";
            if (task.getStatus()) {
                status = "done";
            } else {
                status = "pending";
            }
            if (task.getType().equals("todo")) {
                output = "todo " + status + " " + task.getDescription();
                fw.write(output + "\n");
            } else if (task.getType().equals("event")) {
                output = "event " + status + " " + task.getDescription();
                fw.write(output + "\n");
                fw.write(task.getExtraInfo() + "\n");
            } else if (task.getType().equals("deadline")) {
                output = "deadline " + status + " " + task.getDescription();
                fw.write(output + "\n");
                fw.write(task.getExtraInfo() + "\n");
            } else {
                throw new DukeException("     Invalid file type!");
            }
        }
        fw.close();
    }
}
