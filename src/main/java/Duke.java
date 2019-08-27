import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.DukeIllegalDescriptionException;
import duke.exceptions.DukeIllegalInputException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            create();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("/Users/auxin/duke/data/Tasks.txt");
        duke.greet();
        while (Ui.getFlag()) {
            duke.run();
        }
    }

    private void create() {
        storage.createFolder();
        storage.createFile();
        tasks = new TaskList();
    }

    private void greet() {
        ui.greet();
    }

    private void run() {
        try {
            ui.scan();
        } catch (DukeIllegalInputException | DukeIllegalDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }
}
