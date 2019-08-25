
public class Duke {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor
     *
     * @param filePath location of file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Main loop which takes in command and execute
     * until isExit = true
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main driver
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("C:/Users/User/Documents/GitHub/duke/src/main/tasks.txt").run();
    }

}
