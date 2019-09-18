import java.io.IOException;

/**
 * Duke is a task manager programme with the following capabilities:
 * addition of tasks, listing of tasks, deletion of tasks and marking tasks as done.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates Duke object.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage("data/tasks.txt");
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }

    /**
     * Runs the programme continuously until the user inputs "bye".
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                ui.showReply(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Takes the input string entered into the text box and returns the
     * appropriate output response to be displayed in the dialog box.
     * @param input refers to the string message entered by the user.
     * @return appropriate reply expected
     * @throws IOException if stream to file cannot be written to or closed.
     * @throws DukeException if the user input is invalid.
     */
    public String getResponse(String input) throws IOException, DukeException {
        String fullCommand = input;
        Command c = Parser.parse(fullCommand);
        String reply = c.execute(tasks, ui, storage);
        return reply;
    }
}