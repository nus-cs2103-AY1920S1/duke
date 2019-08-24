import java.util.Date;

public class Duke {

    Storage storage;
    TaskList taskList;
    Ui ui;
    DataParser parser;
    DateParser dateHelper;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new DataParser();
        dateHelper = new DateParser();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.sendGreeting();
        boolean hasTerminated = false;

        while (!hasTerminated) {
            if (!parser.hasAnymoreData()) {
                break;
            }

            try {
                parser.readInput();
                Command c = parser.findCommand();
                hasTerminated = c.isExit;
                c.execute(taskList, ui, storage, parser, dateHelper);
            } catch (DukeException error) {
                ui.sendErrorMessage(error);
            }
        }
        ui.sendFarewell();;
    }

}
