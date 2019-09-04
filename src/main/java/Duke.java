package duke;

import java.io.FileNotFoundException;
import duke.command.TaskList;
import duke.command.Ui;
import duke.command.Parser;
import duke.command.Storage;
import duke.DukeException;

/**
 * Represents the main class of the programme.
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a duke object that takes in filePath which is the
     * path from which the file is to be loaded.
     * @param filePath The path of the file from which the file is loaded.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
            storage.setList(taskList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("tasks.txt");
        duke.run();
    }

    /**
     * Runs the Duke programme.
     */
    public void run() {
        Parser parser = new Parser(this.taskList, this.ui, this.storage);
        ui.printWelcomeMessage();
        ui.run(parser);
    }
}
