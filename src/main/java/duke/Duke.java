package duke;

import duke.command.Command;

/**
 * Starts execution of the bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this("data/tasks.txt");
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            return ui.getStringToPrint();
        }
    }
}

