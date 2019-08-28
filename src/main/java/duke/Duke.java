package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import duke.command.CommandNotFoundException;
import duke.parser.IncorrectFileFormatException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.command.Command;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // Initialising Duke
    // Do Loading of tasks, greeting message, set up tasks list
    public Duke(String filePath) {
        Parser.initialize();
        storage = new Storage(filePath);

        ui = new Ui();

        try {
            tasks = new TaskList(storage.load());
        } catch (InvalidPathException i) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IncorrectFileFormatException | FileNotFoundException f) {
            ui.showLoadingError();
        } catch (NullPointerException n) {
            ui.showIndexError();
        }
    }

    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();

                ui.showLine(); // show the divider line ("_______")

                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);

                isExit = c.isExit();

            } catch (IndexOutOfBoundsException o) {
                ui.showIndexError();
            } catch (NullPointerException n) {
                ui.showInputError();
            } catch (CommandNotFoundException c) {
                ui.showCommandNotFoundError();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\user\\Desktop\\CS2103_Git\\duke\\data\\tasks.txt").run();
    }

}












