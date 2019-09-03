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
     * Runs the duke application.
     */
    public void run() {
        ui.printHello();
        String userInput = ui.getNextLine();
        String inputType = userInput.split(" ")[0];

        while (!inputType.equals("bye")) {
            try {
                Parser.parse(tasks, ui, storage, userInput);
                userInput = ui.getNextLine();
                inputType = userInput.split(" ")[0];
            } catch (DukeException e) {
                System.out.println("     " + e.getMessage());
                System.out.println("    _____________________________________\n");
            }
        }

        storage.save(tasks.getListOfTasks());
        ui.printBye();
    }

    /**
     * Gets response every time user send inputs.
     *
     * @param input User's input to the duke application.
     * @return response to the user's input.
     */
    public String getResponse(String input) {
        if (Parser.hasTerminated()) {
            return ui.print();
        }
        try {
            Parser.parse(tasks, ui, storage, input);
            return ui.print();
        } catch (DukeException e) {
            ui.sendErrorMessage(e.getMessage());
            return ui.print();
        }
    }
}