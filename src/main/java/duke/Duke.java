package duke;

import command.Command;
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

            if(isExit) {
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
    public void startup(String filePath) {
        ui = new UserInterface();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | ParseException e) {
            ui.showError(e.getMessage());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public UserInterface getUi() {
        return ui;
    }
}