package storage;

import parser.Parser;
import tasks.Task;
import util.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

/**
 * Encapsulates a handler that deals with storage - loading tasks from
 * file and saving to file.
 *
 *  @author atharvjoshi
 *  @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class Storage {
    /** File object containing the saved task list. */
    private File file;

    /** The string represnting file path relativ to root folder. */
    private String filePath;

    /**
     * Creates a storage handler with a given filepath.
     *
     * @param filePath the path to the file object containing the task list
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Loads tasks into the task list from the saved file, every time ui.Duke is
     * re-initialised.
     *
     * @param emptyList the list the tasks are to be loaded into
     * @throws FileNotFoundException if file specified by filepath is not found
     * @throws ParseException if wrong date/time format encountered
     */
    public void loadToList(TaskList emptyList) throws FileNotFoundException,
            ParseException, IOException {
        // create file if it doesn't already exist
        if (!this.file.exists()) {
            this.file.getParentFile().mkdir();
            this.file.createNewFile();
        }
        // read file using Scanner class
        Scanner s = new Scanner(this.file);
        while (s.hasNext()) {
            Parser.parseTextToTask(s.nextLine(), emptyList);
        }
    }

    /**
     * Updates task list whenever an add, done, or delete command is executed.
     *
     * @param modifiedList the list instance after command is executed.
     * @throws IOException if file not found
     */
    public void update(TaskList modifiedList) throws IOException {
        if (!modifiedList.isEmpty()) {
            int listSize = modifiedList.size();
            for (int i = 0; i < listSize; i++) {
                Task currentTask = modifiedList.get(i);
                assert currentTask != null;
                this.writeToFile(currentTask.toString());
            }
        }
        try {
            if (!modifiedList.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                // loop through each task in list and append to string builder.
                int listSize = modifiedList.size();
                for (int i = 0; i < listSize; i++) {
                    Task currentTask = modifiedList.get(i);
                    assert currentTask != null;
                    sb.append(currentTask.toString());
                    sb.append("\n");
                }
                assert !sb.toString().isEmpty();
                this.writeToFile(sb.toString());
            }
        } catch (IOException exception) {
            System.out.println("Sorry. I can't save this change :-(");
        }
    }

    /**
     * Writes given text to the specified file on the hard disk.
     *
     * @param textToAdd the text to be added
     * @throws IOException if file not found
     */
    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.write("\n"); // add newline
        fw.close();
    }
}

