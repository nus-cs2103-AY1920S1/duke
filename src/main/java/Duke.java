import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke() throws DukeException {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        this.taskList = new TaskList(this.storage.load(), this.storage);
        }


    private void run() {
        this.ui.showWelcomeMessage();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(this.taskList, this.ui);
                if (command.getCommandType().equals(CommandType.EXIT)) {
                    break;
                }
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) throws DukeException{
        new Duke().run();
    }
}
