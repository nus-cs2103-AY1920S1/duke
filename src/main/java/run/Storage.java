package run;

import exception.DukeException;
import exception.ParseFileException;
import exception.UpdateStateException;
import parser.StorageParser;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage system that writes to and from file in hard disk based on state on TaskList.
 */
public class Storage {
    protected String filepath;
    protected File state;
    protected StorageParser sp = new StorageParser();

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String USER_DIRECTORY = System.getProperty("user.dir");

    /**
     * Constructor for Storage.
     * @param filepath filepath to state file
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        init();
    }

    /**
     * Initialises by checking if file is present in specified path and creates file if none present.
     */
    public void init() {
        state = new File(filepath);
        try {
            if (state.createNewFile()) {
                Ui.showMessage("No file detected, state file created!");
            } else {
                Ui.showMessage("State file detected!");
            }
        } catch (IOException ex) {
            Ui.showErrorMessage("IO exception encountered while initializing state file!");
        }
    }

    /**
     * Creates tasks from state file and stores them in an arraylist for later conversion into TaskList object.
     * @return An arraylist of tasks based on tasks created and loaded from state file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = null;
        try {
            sc = new Scanner(state);
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (nextLine.equals("")) {
                    break;
                } else {
                    Task currTask = sp.parseLine(nextLine);
                    assert currTask != null;
                    tasks.add(currTask);
                }
            }
        } catch (IOException ex) {
            Ui.showErrorMessage("IO exception caught while loading state file, initializing new empty Task List!");
            return new ArrayList<Task>();
        } catch (ParseFileException ex) {
            Ui.showErrorMessage("Exception while reading contents of state file, initializing new empty Task List!");
            return new ArrayList<Task>();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return tasks;
    }

    /**
     * Rewrites state file based on current tasks in TaskList.
     * @param tasks all current tasks (the entire TaskList itself)
     * @throws IOException if exception when accessing file
     * @throws UpdateStateException if exception when writing contents to file
     */
    public void updateState(TaskList tasks) throws IOException, UpdateStateException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filepath);
            StringBuilder textToAddSB = new StringBuilder();
            for (Task currTask : tasks.getTasksArrayList()) {
                if (currTask instanceof ToDo) {
                    textToAddSB.append(fileUpdateToDo((ToDo) currTask));
                    textToAddSB.append(System.lineSeparator());
                } else if (currTask instanceof Deadline) {
                    textToAddSB.append(fileUpdateDeadline((Deadline) currTask));
                    textToAddSB.append(System.lineSeparator());
                } else if (currTask instanceof Event) {
                    textToAddSB.append(fileUpdateEvent((Event) currTask));
                    textToAddSB.append(System.lineSeparator());
                } else {
                    throw new UpdateStateException(DukeException.UPDATE_STATE_EXCEPTION_MESSAGE);
                }
            }
            fw.write(textToAddSB.toString());
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    /**
     * Converts todo object into formatted String representation for storage on state file.
     * @param todo todo object to be written to state file
     * @return String of todo formatted for sending for writing to state file
     */
    public String fileUpdateToDo(ToDo todo) {
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append("T//");
        stringBuilder.append(todo.getName());
        stringBuilder.append("//");
        stringBuilder.append(todo.checkIsDone());
        return stringBuilder.toString();
    }

    /**
     * Converts deadline object into formatted String representation for storage on state file.
     * @param deadline deadline object to be written to state file
     * @return String of deadline formatted for sending for writing to state file
     */
    public String fileUpdateDeadline(Deadline deadline) {
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append("D//");
        stringBuilder.append(deadline.getName());
        stringBuilder.append("//");
        stringBuilder.append(deadline.checkIsDone());
        stringBuilder.append("//");
        stringBuilder.append(deadline.getStringBy());
        return stringBuilder.toString();
    }

    /**
     * Converts event object into formatted String representation for storage on state file.
     * @param event event object to be written to state file
     * @return String of event formatted for sending for writing to state file
     */
    public String fileUpdateEvent(Event event) {
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append("E//");
        stringBuilder.append(event.getName());
        stringBuilder.append("//");
        stringBuilder.append(event.checkIsDone());
        stringBuilder.append("//");
        stringBuilder.append(event.getStringAt());
        return stringBuilder.toString();
    }
}