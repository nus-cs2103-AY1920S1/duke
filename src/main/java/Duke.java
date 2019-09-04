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
    private Parser parser;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        String filePath = "data/duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            parser = new Parser(tasks);
        } catch (DukeException e) {
            ui.showError(e.toString());
            tasks = new TaskList();
            parser = new Parser(tasks);
        }
    }

    /**
     * Starts Duke.
     */
    public void run() {
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);

        System.out.println(ui.showWelcome());

        while(!isExit) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println(parser.parse(input));
                isExit = true;
            } else {
                System.out.println(parser.parse(input));
            }
        }
        sc.close();
        storage.write(tasks);
    }

    public String getResponse(String input) {
        if(input.equals("bye")) {
            storage.write(tasks);
        }
        return parser.parse(input);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
