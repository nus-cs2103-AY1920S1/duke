package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;
    File file;
    Ui ui;

    /**
     * Initialize a Storage object.
     *
     * @param filePath Path of file whether data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        ui = new Ui();
    }

    /**
     * Load data from file specified by filePath.
     *
     * @return ArrayList of tasks.
     * @throws DukeException If no file is found at FilePath.
     */
    public List<Task> load() throws DukeException {
        try {
            Scanner sc = new Scanner(file);
            if (!sc.hasNext()) {
                throw new DukeException("No records found");
            }

            List<Task> tasks = new ArrayList<>();
            while (sc.hasNext()) {
                tasks.add(Task.parseFromData(sc.nextLine()));
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Load file not found");
        }
    }

    /**
     * Save program data to file at filePath.
     *
     * @param taskList List of tasks to be written to file.
     */
    public void save(TaskList taskList) throws DukeException {
        if (taskList.isEmpty()) {
            ui.showNothingToSaveMsg();
        }

        try {
            FileWriter fw = new FileWriter(file);
            for (Task t : taskList.getTasks()) {
                fw.write( t.toStorageString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error saving to file");
        }
    }
}
