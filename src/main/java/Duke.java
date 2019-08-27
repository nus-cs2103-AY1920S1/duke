import java.io.IOException;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import static java.lang.String.format;

/**
 * Duke class.
 */
public class Duke {
    private String line;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke Constructor.
     *
     * @param filePath path of data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException duke) {
            ui.showLoadingError(duke);
            tasks = new TaskList();
        }
    }


    private void run() {
        boolean isExit = false;
        ui.printStatement("Hello! I'm Duke", "What can I do for you?");
        while (!isExit) {
            String fullCommand = ui.readCommand();
            // !sc.hasNext("bye")
            Command c = new Command(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        Duke d = new Duke("./data/list.txt");
        d.run();
    }
}
