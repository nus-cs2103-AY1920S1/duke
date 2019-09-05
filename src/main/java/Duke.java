import duke.exception.DukeException;
import duke.frontend.Ui;
import duke.exception.EmptyListException;
import duke.storage.Storage;
import duke.task.TaskList;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList list;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            list = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            list = new TaskList();
        }
        ui = new Ui(list);
        // include parser here later on
    }

    public void run() throws DukeException {
        // initialize a duke bot to take input and perform actions
        ui.start();
        storage.save(ui.getFinalList());
    }

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String dir = System.getProperty("user.dir") + "/src/main/java/data/dukeData.txt";
        Duke duke = new Duke(dir);
        duke.run();
    }
}
