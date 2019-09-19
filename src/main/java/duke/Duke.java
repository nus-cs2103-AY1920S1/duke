package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.io.IOException;


/**
 * This class is the main class of the program. It helps users to help track their Duke.tasks
 * by creating a task list for them when users key in the respective input. The task list
 * is then automatically saved into a txt file which allows the users to view and keep track.
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    /**
     * Main application.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage("duke.txt");
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
    
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}


