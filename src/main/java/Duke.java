
public class Duke {
    private static final String TABS = "     ";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Replace this stub with completed method.
     */
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Constructor for Duke object.
    */
    public Duke() {
        ui = new Ui();
        storage = new Storage("//Users//chowjiaying//Github//2103T-iP//duke//data//duke.txt");
        tasks = new TaskList(storage.load());
    }

    /**
     * Prints a welcome message.
     */
    public String getWelcome() {
        return TABS + "Hello! I'm Duke\n"
                + TABS + "What can I do for you?";
    }

    /**
     * Method that runs Duke logic.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String userCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(userCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
            } finally {
                ui.showLine();
            }

        }
    }
}
