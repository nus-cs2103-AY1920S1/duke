import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.FileNotFoundException;


public class Duke {

    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException, ParseException{
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() throws ParseException, IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException{
        new Duke("duke.txt").run();
    }
}
