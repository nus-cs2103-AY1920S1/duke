import java.io.FileNotFoundException;
import java.text.ParseException;

class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private Duke() {
        this.ui = new Ui();
        this.storage = new Storage("duke.txt");
        try {
            this.tasks = storage.loadTasks();
        } catch (FileNotFoundException | ParseException ex) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    private void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            this.ui.showLine();
            Command command;
            try {
                command = Parser.parseCommand(fullCommand);
                isExit = command.execute(this.tasks, this.ui, this.storage);
            } catch (ParseException e) {
                this.ui.showInvalidDateError();
            } catch (InvalidCommandException e) {
                this.ui.showInvalidCommandError();
            }
            this.ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
