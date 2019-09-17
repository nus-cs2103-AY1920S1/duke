/**
 * Represents the main logic of the applications.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes duke application with the path of the file of the list of tasks.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.load());
    }

    /**
     * Gets response every time user send inputs.
     *
     * @param input User's input to the duke application.
     * @return response to the user's input.
     */
    public String getResponse(String input) {
        try {
            Parser.parse(tasks, ui, input);
            storage.save(tasks.getListOfTasks());
            return ui.print();
        } catch (DukeException e) {
            ui.setErrorMessage(e.getMessage());
            return ui.print();
        }
    }
}