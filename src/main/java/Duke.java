import slave.command.Command;
import slave.command.CommandType;

import slave.elements.Parser;
import slave.elements.Storage;
import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;

public class Duke {

    private Ui ui;
    private TaskList taskList;

    private Duke() throws DukeException {
        this.ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        this.taskList = new TaskList(storage.load(), storage);
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

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
