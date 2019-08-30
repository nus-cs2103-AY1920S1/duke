import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
            parser = new Parser(tasks);
        } catch (DukeException e) {
            //ui.showLoadingError();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine(); // show the divider line ("_______")
            parser.parse(fullCommand);
            ui.showLine();
            isExit = fullCommand.equals("bye");
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
