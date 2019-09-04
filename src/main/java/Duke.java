import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import storage.StorageInterface;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class used to drive the program.
 * @author Wilfred Bradley Tan, A0185405E.
 */
public class Duke {

    /** Object used to execute commands. */
    private Command command = new Command();
    /** User interface to display feedback and instructions to user. */
    private Ui ui = new Ui();
    /** Storage object to facilitate loading and saving of tasks. */
    private StorageInterface storage = new Storage("tasks.txt");
    /** List of tasks added by the user. */
    private TaskList taskList = new TaskList();

    /**
     * Creates Duke with an absolute filePath.
     * @param args String[]
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /** Initiates the system by requesting for user input. Executes the next command after parsed
     * by the Parser and fed to the command object. Error messages will be shown if invalid commands are given.
     */
    public void run() {
        try {
            taskList = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                String userInput = sc.nextLine();
                command = Parser.parse(userInput);

                ui.showLine();
                command.execute(ui, taskList, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
