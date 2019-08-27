import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class Duke {

    private Ui ui;
    private Storage storage;
    private ListManager listManager;
    private SimpleDateFormat formatter;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, formatter);
        formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            listManager = new ListManager(storage.load(), formatter);
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            listManager = new ListManager(formatter);
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
            ui.bar();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
