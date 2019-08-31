import java.io.IOException;

public class Duke {
    /** Storage
     * Acts as the database for Duke.
     */
    private final Storage storage;
    /** Tasks
     * Operations for task list
     */
    private TaskList tasks;
    /** UI
     * User Interface for Duke
     */
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException ie) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = CommandParser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidCommandException ice) {
                ui.showError("Invalid command: " + ice.getInvalidCommand());
            } catch (InvalidParameterException ipe) {
                ui.showError("Invalid parameters: " + ipe.getInvalidParameter());
            } catch (InvalidDateTimeException idte) {
                ui.showError("Invalid date and time");
            }
        }

    }

    public static void main(String[] args) {
        new Duke("/Users/bj/java/Duke/data/duke.txt").run();
    }

}
