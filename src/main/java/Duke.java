public class Duke {

    private UI ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        //load previous task list. If not found, create a new one
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadingSuccessful();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String inputStr = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(inputStr);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
