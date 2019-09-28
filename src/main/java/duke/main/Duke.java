package duke.main;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private final static String FILEPATH = "data/duke.txt";


    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);

            assert c != null : "Command c should not be null";
            
            return c.execute(storage, ui, tasks);
        }
        catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String getWelcome() {
        return ui.getOpening();
    }
}
