package duke.util;

import duke.exception.UnknownStorageEntryException;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Deals with loading tasks from the file and saving tasks in the file. */
public class Storage {
    String filePath;
    File file;
    FileWriter fileWriter;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks saved in an external file into the current list of tasks. Initializes a
     * FileWriter to write into external source.
     *
     * @return List of tasks loaded from external source
     * @throws IOException Thrown when there is a problem with loading the file
     * @throws ParseException Thrown when date provided by user is in the wrong format
     */
    public List<Task> load() throws IOException {
        List<Task> tempTaskList = new ArrayList<>();
        file = new File(filePath);
        fileWriter = new FileWriter(filePath, true);
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            try {
                tempTaskList.add(Parser.parseStorage(fileScanner.nextLine()));
            } catch (Exception e) {
                continue;
            }
        }

        return tempTaskList;
    }

    /**
     * Saves all {@link Task} in {@link TaskList} into the external file.
     *
     * @param tasks Current {@link TaskList} by user
     * @throws IOException Thrown when {@link FileWriter} cannot write into file
     */
    public void saveTaskList(TaskList tasks) throws IOException {
        fileWriter = new FileWriter(filePath); //Clears file
        fileWriter = new FileWriter(filePath, true);
        String input = "";
        for (int i = 0; i < tasks.listSize(); i++) {
            String taskTemp = "\n" + tasks.getByIndex(i).getStorageSyntax();
            input += taskTemp;
        }
        fileWriter.write(input);
        fileWriter.close();
    }

    /**
     * Closes the File Writer.
     *
     * @throws IOException Thrown if there is an error closing the File Writer
     */
    public void close() throws IOException {
        fileWriter.close();
    }
}
