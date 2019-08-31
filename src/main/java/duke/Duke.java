package duke;

import duke.dukeexception.DukeException;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;


/**
 * Class that serves as the main driver for the Duke application.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Class constructor that specifies file path to load storage from.
     *
     * @param filepath File path to load storage from.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
    }

    /**
     * Class constructor that assumes file path to load storage from is in
     * data/duke.txt.
     */
    public Duke(){
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.getLoadingErrorResponse();
            this.taskList = new TaskList();
        }
    }

    /**
     * Returns the Ui object handle inputs and outputs for this Duke instance.
     *
     * @return This Duke instance's Ui object.
     */
    public Ui getUI(){
        return ui;
    }

    private Response process(String input) {
        Response response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(taskList, ui, storage);
        } catch (DukeException de) {
            response = ui.getErrorResponse(de.getMessage());
        }

        return response;
    }

    /**
     * Returns the response after passing user input through Duke's logic.
     *
     * @param input User input from GUI.
     * @return Response returned by Duke's logic.
     */
    public Response getResponse(String input) {
        Response response = process(input);
        return response;
    }
}
