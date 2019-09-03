/**
 * Main class of the application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Setting up several classes ready to be called.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("C:/Users/User/Desktop/duke.txt");

        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else {
            try {
                return Parser.parse(tasks, ui, input, storage);
            } catch (Exception err) {
                return "[Exception]" + err;
            }
        }
    }
}
