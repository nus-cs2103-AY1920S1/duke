package duke.command;

import duke.exceptions.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.util.ArrayList;

/**
 * This class is used to save the tasks in the hard disk once there is a change made to the list and load the file
 * from the hard disk once the duke.Duke is initiated.
 */
public class Storage {

    private static String basePath = new File("").getAbsolutePath();
    private static String filePath;
    private ArrayList<Task> store = new ArrayList<>();

    /**
     * Constructor of the class, assigned the string value to the variable.
     *
     * @param filePath A string to represent the filepath in the hard disk.
     */
    public Storage(String filePath) {
        Storage.filePath = basePath + filePath;
    }

    /**
     * Another constructor of the class.
     */
    public Storage() {

    }

    /**
     * To get the filepath of the class.
     *
     * @return The load and store location.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * To create a new folder in the directory if the specific folder is not existed.
     */
    public void createFolder() {
        String folderPath = basePath + "/data";
        File newFolder = new File(folderPath);
        if (newFolder.mkdir()) {
            System.out.println("Folder is created.");
        }
    }

    /**
     * To create a new file in the directory if the specific file is not existed.
     */
    public void createFile() {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File is created to save tasks.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * To load the file information from the hard disk.
     *
     * @return A list contains tasks loaded from the file.
     * @throws DukeException To deal with the potential loading error.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String currLine;
            while ((currLine = br.readLine()) != null) {
                String[] arr = currLine.split(" - ");
                String type = arr[0];
                String isDone = arr[1];
                String action = arr[2];
                switch (type) {
                case "T":
                    Task todo = new Todo(action);
                    if (isDone.equals("1")) {
                        todo.markAsDone();
                    }
                    store.add(todo);
                    break;
                case "D":
                    String by = arr[3];
                    Task deadline = new Deadline(action, by);
                    if (isDone.equals("1")) {
                        deadline.markAsDone();
                    }
                    store.add(deadline);
                    break;
                case "E":
                    String at = arr[3];
                    Task event = new Event(action, at);
                    if (isDone.equals("1")) {
                        event.markAsDone();
                    }
                    store.add(event);
                    break;
                default:
                    break;
                }
            }
            return store;
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    /**
     * To make the change to the file.
     *
     * @param filePath     The store location of the file.
     * @param textToAppend The change of text made to the file.
     * @param flag         To determine whether overwrite the original file or just append on it.
     */
    void appendToFile(String filePath, String textToAppend, boolean flag) {
        try {
            FileWriter fw = new FileWriter(filePath, flag);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(textToAppend + "\n");
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
