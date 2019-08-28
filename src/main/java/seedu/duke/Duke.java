package seedu.duke;

import seedu.duke.core.DukeController;
import seedu.duke.exception.DukeException;
import seedu.duke.core.Command;
import seedu.duke.core.Storage;
import seedu.duke.model.Task;
import seedu.duke.core.Ui;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String DIRECTORY_PATH = "D:/project/CS2103T/duke/data";
    private static String FILEPATH = DIRECTORY_PATH + "/duke.txt";

    private void run() throws DukeException, IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        DukeController controller = new DukeController();
        Ui ui = new Ui();
        Storage storage = new Storage();
        Command command = new Command();

        File file = storage.initFile();
        List<Task> list = storage.loadTask(FILEPATH);

        ui.showWelcome();
        controller.execute(ui, list, storage, command, sc);

        ui.printLine();
        ui.printByeMessage();
        ui.printLine();
    }

    public static void main(String[] args) throws DukeException, IOException, ParseException {
        new Duke().run();
    }
}

