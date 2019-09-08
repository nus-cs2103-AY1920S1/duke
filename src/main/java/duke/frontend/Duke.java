package duke.frontend;

import duke.exception.DukeException;
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
    }

    public void run() throws DukeException {
        ui.start();
        storage.save(ui.getFinalList());
    }

    public void displayIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void main(String[] args) throws DukeException {
        String dir = System.getProperty("user.dir") + "/src/main/java/data/dukeData.txt";
        Duke duke = new Duke(dir);
        duke.displayIntro();
        duke.run();
    }
}
