import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import duke.ui.Ui;
import duke.backend.Storage;
import duke.backend.ListManager;
import duke.command.Command;
import duke.parser.Parser;


public class Duke {

    private Ui ui;
    private Storage storage;
    private ListManager listManager;
    private SimpleDateFormat formatter;

    public Duke(String filePath) {
        ui = new Ui();
        formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        storage = new Storage(filePath, formatter);
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
