public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Duke object.
     * @param filePath file path of text file that stores tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        new Duke("//Users//chowjiaying//Github//2103T-iP//duke//data//duke.txt").run();
    }

    /**
     * Method that runs the Duke program.
     */
    public void run() {
        ui.showWelcome();
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
