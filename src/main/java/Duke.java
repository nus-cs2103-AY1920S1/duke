import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("../../../data/duke.txt").run();
    }

    public void run() {
        ui.printStartScreen();
        Parser parser = new Parser(ui, tasks, storage);
        String userInput = ui.readLineInput();
        while (!"bye".equals(userInput)) {
            parser.process(userInput);
            userInput = ui.readLineInput();
        }
        ui.printExitMessage();
    }
}
