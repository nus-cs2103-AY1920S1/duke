package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskWithDate;
import duke.ui.ErrorMsg;
import duke.ui.Ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InvalidPropertiesFormatException;

public class Storage {
    static final int NUMBER_OF_SAVE_PARAMS_TASK = 3;
    protected String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Load from save file (txt file in hard disk) (path currently hardcoded).
     *
     * @param ui ui object
     * @return list of tasks
     * @throws IOException IO exception
     * @throws DukeException generic exception with error message
     */
    public TaskList loadTaskListFromSaveFile(Ui ui) throws DukeException {
        TaskList results = new TaskList();
        //Read the content from file
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = bufferedReader.readLine();
            while (line != null) {
                //Process
                processLineFromSaveFile(results, ui, line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            ui.storageFileNotFoundError();
        } catch (InvalidPropertiesFormatException e) {
            ui.storageInvalidLineError();
        } catch (IOException e) {
            ui.storageIoExceptionError();
        }
        //Return this by default
        return results;
    }

    /**
     * Process line(s) obtained from loadFromSaveFile.
     *
     * @param taskList List of tasks
     * @param ui  ui object
     * @param line String, a line from the save text file
     * @throws DukeException generic exception with error message
     */
    public void processLineFromSaveFile(TaskList taskList, Ui ui, String line) throws DukeException {
        String[] data = line.split("\\|");
        if (data.length == NUMBER_OF_SAVE_PARAMS_TASK) {
            taskList.insertByCommand(ui, data[0], data[1]);
            taskList.get(taskList.size() - 1).markAsDone(data[2].equals("true"));
        } else {
            throw new DukeException(ErrorMsg.ERROR_IN_PARSING_SAVEFILE);
        }
    }

    /**
     * Save task list to txt file in hard disk (path currently hardcoded).
     *
     * @param taskList List of tasks
     * @throws DukeException generic exception with error message
     */
    public void saveTaskListToFile(TaskList taskList) throws DukeException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path, "UTF-8");
            //Each line is saved as like "input", so that saving and input can use the same code
            taskList.stream().map((task) -> {
                StringBuilder sb = new StringBuilder();
                String taskCommand = task.getTaskName().toLowerCase();
                sb.append(taskCommand);
                sb.append("|").append(taskCommand);
                sb.append(" ").append(task.getDescription());
                if (task instanceof Event) {
                    sb.append(" /at ").append(((TaskWithDate) task).getParseableDateToString());
                } else if (task instanceof Deadline) {
                    sb.append(" /by ").append(((TaskWithDate) task).getParseableDateToString());
                }
                sb.append("|").append(task.isDone());
                return sb;
            }).forEach((writer::println));
        } catch (IOException e) {
            throw new DukeException(ErrorMsg.ERROR_IN_WRITING_TO_SAVEFILE);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
