import duke.command.Storage;
import duke.command.Ui;
import duke.list.TaskList;
import duke.excaptions.IllegalDukeArgumentException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui(storage);
        try {
            taskList = new TaskList(storage.textRead());
        } catch (IllegalDukeArgumentException e) {
            ui.showError("Loading Error!\nPlease check the file.");
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.readCommand();
        ui.showLine();
    }

    private void greeting() {
        ui.showWelcome();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("Users/xutunan/duke/duke.txt");
        duke.greeting();
        duke.ui.showLine();
        while (!Ui.getIsExit()) {
            duke.run();
        }

    }
}


