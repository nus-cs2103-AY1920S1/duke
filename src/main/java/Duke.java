import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("../../../data/duke.txt").run();
    }

    public void run() {
        ui.showWelcome();
        while (ui.sc.hasNextLine()) {
            String command = ui.readCommand();
            Parser.parse(command, tasks, storage, ui);
        }
    }

}
