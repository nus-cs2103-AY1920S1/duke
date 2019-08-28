package duke.storage;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Storage is where files are accessed and written for Duke.
 */
public class Storage {
    private File file;
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Saves a string to the save-file.
     *
     * @param stringToSave  string that will be saved to a file
     */
    public void saveDuke(String stringToSave){
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(stringToSave);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
    }

    /**
     * Loads the save-file to Duke.
     *
     * @return TaskList that was saved in the save-file.
     * @throws DukeException  If there was an error in the save-file and TaskList does not generate properly.
     * @throws FileNotFoundException If there is an error in the file path, or the file does not exists.
     * @throws ParseException If there is an error in parsing.
     */
    public TaskList load() throws DukeException, FileNotFoundException, ParseException {
        TaskList tl = new TaskList();
        Scanner fileSc = new Scanner(this.file);
        String line;
        while (fileSc.hasNext()) {
            line = fileSc.nextLine();
            tl.parseInput(line, false);
            if (Boolean.parseBoolean(fileSc.nextLine())) {
                tl.checkTask(tl.getTaskList().size() - 1);
            }
        }
        System.out.println("Loaded saved task list!");
        return tl;
    }
}
