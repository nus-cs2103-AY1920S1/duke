package duke;

import command.Command;
import exception.CorruptedDataException;
import exception.DukeException;
import task.TaskList;
import ui.UserInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Runs the program skeleton: Event flow.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UserInterface ui;

    private boolean isDataLoaded = false;
    private boolean isFileFound = false;

    public Duke(String filepath) {
        startup(filepath);
    }

    /**
     * Parses user inputs and return appropriate replies.
     * @param input User's action.
     * @return Reply from duke.
     */
    public String getResponse(String input) {
        try {
            boolean isExit = false;

            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            storage.save(tasks.getAllTasks());

            if (isExit) {
                return ui.goodbye();
            } else {
                return c.toString();
            }

        } catch (DukeException | ParseException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Constructs Duke object.
     * @param filePath Specified file destination.
     */
    private void startup(String filePath) {
        ui = new UserInterface();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            isDataLoaded = true;
            isFileFound = true;
        } catch (IOException | ParseException e) {
            isFileFound = false;
        } catch (DukeException e) {
            isDataLoaded = false;
            tasks = new TaskList();
        }
    }

    /**
     * Gets the string status of data loading for the task list.
     * @return String statement of success/failure
     */
    public String getLoadFileResponse() {
        if (isDataLoaded) {
            return ui.showLoadingSuccess();
        } else {
            return ui.showLoadingError(new CorruptedDataException().getMessage());
        }
    }

    public String getFileFoundResponse(String msg) {
        return isFileFound ? ui.showSuccess(msg) : ui.showError(msg);
    }

    public UserInterface getUi() {
        return ui;
    }
}