package seedu.duke;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static seedu.duke.Task.DATE_FORMAT;

/**
 * Represents the storage that handles the loading of tasks from the file and saving of tasks in the file.
 */
public class Storage {

    String filePath;
    File file;

    /**
     * Constructor of the Storage class.
     * Creates a new file if it does not exists.
     *
     * @param filepath the filepath to the data file where the list of tasks will be stored
     * @throws IOException exception thrown if wrong input
     */
    public Storage(String filepath) throws IOException {
        file = new File(filepath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        this.filePath = filepath;
    }

    /**
     * Read in the data stored in the datafile if it exists.
     * Creates a new arraylist and add the tasks on the datafile to the arraylist.
     *
     * @param fr the filereader that reads the file
     * @return the arraylist containing the tasks in the datafile
     * @throws IOException if incorrect input
     * @throws ParseException if the date and time in the datafile are in wrong format
     */
    private static ArrayList<Task> readFile(FileReader fr) throws IOException, ParseException {
        ArrayList<Task> taskList = new ArrayList<>();
        BufferedReader br = new BufferedReader(fr);
        String line = null;

        while ((line = br.readLine()) != null) {
            Task t = null;
            String[] splitArr = line.split(" [|] ");
            if (splitArr[0].equals("T")) {
                t = new Todo(splitArr[2]);
            } else if (splitArr[0].equals("E")) {
                Date dateTime = DATE_FORMAT.parse(splitArr[3]);
                t = new Event(splitArr[2], dateTime);
            } else if (splitArr[0].equals("D")) {
                Date dateTime = DATE_FORMAT.parse(splitArr[3]);
                t = new Deadline(splitArr[2], dateTime);
            }
            if (splitArr[1].equals("1")) {
                t.markAsDone();
            }
            taskList.add(t);
        }
        return taskList;
    }

    /**
     * Returns the arraylist of tasks in the datafile.
     *
     * @return the arraylist of tasks in the datafile
     * @throws IOException if the input is wrong
     * @throws ParseException if the date and time in the datafile are in wrong format
     */
    public ArrayList<Task> load() throws IOException, ParseException {
        return readFile(new FileReader(file));
    }

    /**
     * Writes new list of tasks to the datafile, overwriting previous tasks.
     *
     * @param taskList the arraylist of tasks to be written
     * @throws IOException on wrong input
     */
    public void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskList.getSize(); i++) {
            if (i == 0) {
                fw.write(taskList.getTask(i).writeToFile());
            } else {
                fw.write(System.lineSeparator() + taskList.getTask(i).writeToFile());
            }
        }
        fw.close();
    }

    /**
     * Appends the latest(last) task in the arraylist to the datafile.
     *
     * @param taskList  the arraylist of tasks
     * @throws IOException on wrong input
     */
    public void appendToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        if (taskList.getSize() == 1) {
            fw.write(taskList.getTask(taskList.getSize() - 1).writeToFile());
        } else {
            fw.write(System.lineSeparator() + taskList.getTask(taskList.getSize() - 1).writeToFile());
        }
        fw.close();
    }

}
