import java.io.FileNotFoundException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private ListManager listManager;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            listManager = new ListManager(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            listManager = new ListManager();
        }
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.inputCommand();
            ui.bar();
            Command c = Parser.parse(fullCommand);
            c.execute(listManager, ui, storage);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
