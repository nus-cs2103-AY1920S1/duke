import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(tasks, ui);
    }

    public void run() {
        ui.loadUi(parser);
        storage.save(tasks);
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}