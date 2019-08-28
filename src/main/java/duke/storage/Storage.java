package duke.storage;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Storage {
    private File file;
    public Storage(String filePath) {
        file = new File(filePath);
    }

    public void saveDuke(String stringToSave){
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(stringToSave);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
    }

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
