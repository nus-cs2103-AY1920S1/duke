package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.model.Command;
import seedu.duke.model.Storage;
import seedu.duke.model.Task;
import seedu.duke.model.Ui;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Duke{

    private void run() throws DukeException, IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        DukeController controller = new DukeController();
        Ui ui = new Ui();
        Storage storage = new Storage();
        Command command = new Command();

        File file = storage.initFile();
        List<Task> list = storage.loadTask();

        ui.showWelcome();
        controller.execute(ui, list, storage, command, sc);
        ui.printByeMessage();
        ui.printLine();
    }

    public static void main(String[] args) throws DukeException, IOException, ParseException{
        new Duke().run();
    }
}

