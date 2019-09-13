package duke;

import duke.command.Command;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {

    /*
        Alas - OOP, using a sledgehammer to crack a nut.
        Yet another assignment with disproportionate workload #NUSLife
    */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        if (new File(filePath).exists()) {
            try {
                tasks = new TaskList(storage.load());
                ui.showLoading(tasks.getSize());
            } catch (DukeException | FileNotFoundException ex) {
                ui.showLoadingError(ex.getMessage());
                tasks = new TaskList();
            }
        } else {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage, fullCommand);
                isExit = c.isExit();
            } catch (DukeException | IOException ex) {
                ui.showError(ex.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Program entry point.
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke("." + File.separator + "duke.txt").run();
    }

    public static void varargs(String... args) {
        for (String s : args) {
            System.out.println(s);
        }
        System.out.println("Wow, do I get a prize? #objectivestotallymet #totallylearning");

    }

}
