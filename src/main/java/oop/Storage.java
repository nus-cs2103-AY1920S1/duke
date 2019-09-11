package oop;

import exceptions.DukeException;

import tasks.Task;
import tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 * The Storage class handles all reading of data from files and writing of data to
 * the files.
 */
public class Storage {
    /**
     * The file path to the text file, where data is read from and written to.
     */
    private String filePath;

    /**
     * Constructs and initializes the attributes of a new Storage object.
     * @param filePath The file path to the text file, where data is read from
     and written to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads and reads data from the file specified in the file path.
     * @return Returns the data read as a String to be processed by TaskList.
     * @throws DukeException Throws a DukeException in the event the intended
     file is not found at the file path.
     */
    public String load() throws DukeException {
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            StringBuilder sb = new StringBuilder(sc.nextLine());

            while (sc.hasNextLine()) {
                sb.append("\n" + sc.nextLine());
            }
            return sb.toString();
        } catch (Exception e) {
            throw new DukeException("File not found");
        }
    }

    /**
     * Writes the data retrieved from TaskList to the text file.
     * @param list The TaskList where updated data is retrieved from, and
     *       to be written to the text file.
     */
    public void writeToFile(TaskList list) {
        try {
            FileWriter fileWriter = new FileWriter("data/duke.txt", false);
            for (Task t : list.getTaskList()) {
                String task = t.getFormattedString() + "\n";
                fileWriter.write(task);
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

