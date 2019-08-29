import java.io.IOException;

public class Duke {

    private Storage storage;
    private Sheet sheet;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(myPaths.TASK_LIST);
        try {
            sheet = new Sheet(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        parser.start(sheet);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
