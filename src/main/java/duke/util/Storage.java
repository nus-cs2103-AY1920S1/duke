package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class storing a file attribute. All data with regards to tasks created during the usage of Duke will be
 * stored in a data file via the Storage class upon termination of the Duke UI.
 */
public class Storage {

    /** String representing the path of the storage file */
    private String filePath;

    /**
     * Creates a new storage with a path to the storage File.
     *
     * @param filePath Path of the file that stores all user data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Function that loads data from a storage file and returns an ArrayList with all stored tasks initiated as
     * Task objects.
     *
     * @return An ArrayList of Task objects stored in the data storage file.
     * @throws FileNotFoundException If the data file does not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> fileContent = new ArrayList<>();
        fileContent.add(new Task("T", "Task 0"));
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        assert file.exists() == true : "The data file does not exist";
        Scanner fileScan = new Scanner(file);
        while (fileScan.hasNext()) {
            String fileLine = fileScan.nextLine();
            String arr[] = fileLine.split(" \\| ");
            String type = arr[0];
            switch (type) {
                case "T":
                    fileContent.add(new Task(arr[0], arr[1], arr[2], arr[3]));
                    break;
                case "D":
                    fileContent.add(new Deadline(arr[0], arr[1], arr[2], arr[4], Parser.formatDate(arr[3])));
                    break;
                case "E":
                    fileContent.add(new Event(arr[0], arr[1], arr[2], arr[4], Parser.formatDate(arr[3])));
                    break;
            }
        }

        return fileContent;
    }

    /**
     * Saves all Task objects in a TaskList in a data file by rewriting all the contents of the data file with
     * the most updated version of the TaskList.
     *
     * @param tasks TaskList containing all the Task objects to be saved onto the data file
     * @throws IOException If the writing operation to the data file failed.
     */
    public void save(TaskList tasks) throws IOException {
        assert filePath != null: "File path cannot be empty";
        BufferedWriter fw = new BufferedWriter(new FileWriter(filePath));
        ArrayList<Task> list = tasks.getList();
        for (int i = 1; i < list.size(); i++) {
            fw.write(list.get(i).fileFormat());
        }
        fw.close();

    }
}
