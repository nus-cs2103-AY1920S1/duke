import duke.command.Ui;
import duke.command.Parser;
import duke.command.Executor;
import duke.command.DukeException;
import duke.command.Storage;
import duke.command.TaskList;

import java.util.Scanner;
import java.io.IOException;

/**
 * Represents an application that manages <code>ToDo</code>s, <code>duke.task.Event</code>s, and <code>Deadline</code>s.
 * A Duke object can add <code>Task</code>s, delete them, mark them as Done, and maintain a history of Tasks entered
 * during earlier execution.
 */
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke() {
        this("C:/Users/mtg-1/OneDrive/Documents/NUS/Y2S1/CS2103/repos/dukerepo/src/main/java/history.txt");
    }

    /**
     * Constructs a Duke object that saves Tasks in the provided filePath.
     * @param filePath the path of the text file which is a directory for the Tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException de) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (DukeException de) {
            ui.showException(de);
        }
    }

    /**
     * Integrates <code>Parser</code>, <code>TaskList</code>, <code>Storage</code>, and <code>Ui</code> to
     * deal with User's commands and accordingly manage database.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            try {
                Parser commandAnalyzer = new Parser(command);
                System.out.print(Executor.execute(commandAnalyzer, ui, tasks, storage));
            } catch (DukeException de) {
                System.err.println(de.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("C:/Users/mtg-1/OneDrive/Documents/NUS/Y2S1/CS2103/repos/dukerepo/src/main/java/history.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Parser commandAnalyzer = new Parser(input);
            return Executor.execute(commandAnalyzer, ui, tasks, storage); //returns Duke's response to command
        } catch (DukeException de) {
            return ui.showException(de);
        }
    }

    String getWelcome() {
        return ui.showWelcome();
    }
}
