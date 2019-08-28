import duke.core.Storage;
import duke.core.Ui;
import duke.core.Parser;
import duke.command.Command;
import duke.helper.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> textEntered;
    private Storage storage;
    private TaskList tasks;
    private Ui userIF;

    public Duke() {
        this.userIF = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.outputFileContents());
        } catch (DukeException e) {
            e.getMessage();
            tasks = new TaskList();
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        userIF.printHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = userIF.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, userIF, storage);
                isExit = c.shouldExit();
            } catch (DukeException e) {
                userIF.printError(e.getMessage());
            }
        }
    }

}
