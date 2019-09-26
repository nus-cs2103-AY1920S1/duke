package duke;

import Exception.DukeException;
import helper.Parser;
import task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
    }

    /**
     * Creates a duke.Duke object.
     *
     * @param filePath storage path.
     */
    public Duke(String filePath) {
        assert !filePath.equals(null) : "No file path detected";
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            cmd.execute(tasks, ui, storage);
        } catch (DukeException ex) {
            ui.setError(ex.getMessage());
        } finally {
            return ui.showLine();
        }
    }

    public void changeFilePath(String filePath) throws DukeException {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the main program.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command cmd = Parser.parse(fullCommand);
                cmd.execute(tasks, ui, storage);
                isExit = cmd.isExit();
            } catch (DukeException ex) {
                ui.setError(ex.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


}
