import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * Duke is a chat based task manager that keeps track of the Tasks keyed in by the user.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Duke object.
     * 
     * @param filePath the file path of a file that is written to and from when the program is executed.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.toString());
            tasks = new TaskList();
        }
    }

    /**
     * Starts Duke.
     */
    public void run() {
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks);

        ui.showWelcome();

        while(!isExit) {
            String input = sc.nextLine();
            isExit = parser.parse(input);
        }
        sc.close();
        storage.write(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
