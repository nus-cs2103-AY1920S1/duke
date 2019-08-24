import java.io.IOException;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.dukePrint(e.toString());
        }
    }

    public void start() throws IOException {
        ui.showWelcome();
        String input;
        boolean isExit = false;
        while (!isExit) {
            try {
                input = ui.getInputLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.dukePrint(e.toString());
            }
        }
    }
}
