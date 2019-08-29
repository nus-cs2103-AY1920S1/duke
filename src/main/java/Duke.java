public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            ui.showLoadingError(ex);
            tasks = new TaskList();
        }
    }

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
            } catch (DukeException ex) {
                ui.showErrorMessage(ex);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void printTotalTask() {
        boolean isPlural = tasks.size() > 1;
        System.out.println("Now you have " + tasks.size() + " task"
                + (isPlural ? "s" : "") + " in the list.");
    }
}
