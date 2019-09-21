import commands.Command;
import exceptions.DukeException;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.Ui;


public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private static final String filePath = "data/duke.txt";


    /**
     * The constructor needed to kickstart JavaFx.
     *
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            System.out.println(ui.showErrors(e.getMessage()));
        }
    }

    /**
     * Obtains user input from command line, parses it and executes the commands.
     * Shows errors to the user in case of invalid input.
     *
     * @return String feedback for gui to display
     */
    public String getResponse(String input) {
        Command c;
        try {
            c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showErrors(e.getMessage());
        }
    }

}
