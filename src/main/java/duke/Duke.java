package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.util.Scanner;

/**
 * Main class for Duke application.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a Duke object with disk storage located at filepath".
     *
     * @param filePath filepath for disk storage
     */
    public Duke(String filePath) {
        assert filePath != null : "Filepath cannot be null.";
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.taskList = new TaskList(storage);
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit && sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parseCommand(input);
                ui.print(command.execute(taskList));
                isExit = command.isExit();
            } catch (DukeException e) {
                //CHECKSTYLE.OFF: AvoidEscapedUnicodeCharactersCheck
                ui.print("\u26A0 OOPS! " + e.getMessage());
                //CHECKSTYLE.ON: AvoidEscapedUnicodeCharactersCheck
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Generates a response to user input.
     *
     * @param input User input to be processed.
     * @return response to be printed.
     */
    protected String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return String.join("\n", command.execute(taskList));
        } catch (DukeException e) {
            //CHECKSTYLE.OFF: AvoidEscapedUnicodeCharactersCheck
            return "\u26A0 OOPS! " + e.getMessage();
            //CHECKSTYLE.ON: AvoidEscapedUnicodeCharactersCheck
        }
    }
}
