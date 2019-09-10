import java.io.IOException;

/**
 * The duke.Duke program implements an app that enables users to track their tasks.
 *
 * @author Yen-Peng
 */

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage(taskList);
        Command command = new Command();
        command.execute(taskList, ui, storage);
    }
}