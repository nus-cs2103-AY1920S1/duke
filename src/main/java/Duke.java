import java.io.FileNotFoundException;

public class Duke {

    private static String LIST_PATH = "C:/Users/Yu Han Jeong/Desktop/CS2103T/duke/src/data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        storage = new Storage(LIST_PATH);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        }
        catch (DukeException ex) {
            tasks = new TaskList();
            ui.showError(ex.getMessage());
        }
    }

    public void run() {
        ui.greetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            }
            catch (DukeException ex) {
                ui.showError(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}