/**
 * Duke class.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
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
//        run();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            output = c.execute(this.tasks, ui, this.storage);
        } catch (Exception e) {
            output = Ui.frontSpace + e.getMessage();
        }
        return output;
    }

}
