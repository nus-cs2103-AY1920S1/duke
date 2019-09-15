import duke.commands.Command;
import duke.commands.CommandType;

import duke.core.Storage;
import duke.core.Ui;
import duke.core.TaskList;
import duke.core.Parser;

import duke.errors.DukeException;
import duke.tasks.Task;

import java.io.IOException;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) throws DukeException, IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.load(), this.storage);
        Task.setTaskList(taskList);
    }

    public void run() throws IOException {
        ui.printWelcomeMessage();
        while (ui.hasInputs()) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parseCommand(input);
                command.execute(taskList, this.ui);
                if (command.getCommandType().equals(CommandType.EXIT)) {
                    break;
                }
            } catch (IllegalArgumentException | DukeException error2 ) {
                ui.printErrorMessage(error2);
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("/Users/isaac/Desktop/CS2103T+CS2101/CS2103T/duke/data/duke.txt")
        .run();
    }
   
}
