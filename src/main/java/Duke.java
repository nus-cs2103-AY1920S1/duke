package duke;

import java.io.FileNotFoundException;
import duke.command.TaskList;
import duke.command.Ui;
import duke.command.Parser;
import duke.command.Storage;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

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
        new Duke("tasks.txt").run();
    }

    public void run() {
        Parser parser = new Parser(this.taskList, this.ui, this.storage);
        ui.printWelcomeMessage();
        ui.run(parser);
    }
}
