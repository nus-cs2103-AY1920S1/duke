package duke.storage;

import duke.command.Command;
import duke.command.UnloadableCommand;
import duke.exception.SaveFileWrongFormatDukeException;
import duke.main.Parser;
import duke.task.PriorityLevel;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
     * Saves a string to the file.
     *
     * @param stringToSave The string that is saved to a file.
     */
    public void saveDuke(String stringToSave) {
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(stringToSave);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
    }

    /**
     * Loads the file to Duke.
     *
     * @return A TaskList that is loaded with tasks from the save file.
     * @throws FileNotFoundException If the file path is invalid or the file does not exists.
     * @throws SaveFileWrongFormatDukeException If save file could not be parsed successfully.
     */
    public TaskList load(Parser parser) throws FileNotFoundException, SaveFileWrongFormatDukeException {
        TaskList tl = new TaskList();
        Scanner fileSc = new Scanner(this.file);
        String line;
        int currentTaskIndex = 0;
        while (fileSc.hasNext()) {
            line = fileSc.nextLine();
            Command commandToAddTask = parser.parse(line);
            executeCommandToLoadTask(tl, commandToAddTask);

            if (Boolean.parseBoolean(fileSc.nextLine())) {
                tl.checkTask(currentTaskIndex);
            }

            String priority = fileSc.nextLine();
            setPriorityOfTask(tl, currentTaskIndex, priority);

            currentTaskIndex += 1;
        }
        return tl;
    }

    public void createNewDir(String filePath) {
        File newFolder = new File(filePath);
        boolean createdDir = newFolder.mkdir();
        assert createdDir : "failed to create directory";
    }

    public void createNewSaveFile(String filePath) {
        File newSaveFile = new File(filePath);
        boolean createdFile = false;
        try {
            createdFile = newSaveFile.createNewFile();
        } catch (IOException e) {
            System.out.println("IOException when trying to create a new save file");
        }
        assert createdFile : "file already exists";
    }

    private void executeCommandToLoadTask(TaskList tl, Command c) throws SaveFileWrongFormatDukeException {
        if (c instanceof UnloadableCommand) {
            throw new SaveFileWrongFormatDukeException("Save file contains a line that could not be loaded");
        }
        c.execute(tl);
    }

    private void setPriorityOfTask(TaskList tl, int currentTaskIndex, String priority) throws SaveFileWrongFormatDukeException{
        switch (priority) {
        case "HIGH":
            tl.setPriorityOfTask(currentTaskIndex, PriorityLevel.HIGH);
            break;
        case "MEDIUM":
            tl.setPriorityOfTask(currentTaskIndex, PriorityLevel.MEDIUM);
            break;
        case "LOW":
            tl.setPriorityOfTask(currentTaskIndex, PriorityLevel.LOW);
            break;
        default:
            throw new SaveFileWrongFormatDukeException("Save file contains an invalid priority level");
        }
    }
}
