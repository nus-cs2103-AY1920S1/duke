package duke;

/**
 * This program reads in command, adds new tasks into the task lists, changes the task
 * status when it is done, and delete the tast according to the command.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private CommandGenerator cg = new CommandGenerator();
    private Scanner sc = new Scanner(System.in);

    /**
     * Loads the data from given file path.
     * @param filePath filepath of the data of previous task lists.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    Duke() {}

    /**
     * Greets users, reads in inputs, processes the command, prints the outputs.
     */
    private void run() {
        ui.greet();
        String fullCommand = ui.readInput(sc);

        while (!fullCommand.equals("bye")) {

            try {
                Command command = cg.generateCommand(fullCommand);
                command.execute(tasks, storage);
            } catch (DukeException | IOException ex) {
                System.out.println(ex.getMessage());
            }

            // Scans the next command line.
            fullCommand = ui.readInput(sc);
        }

        ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    String getResponse(String input) {
        return "Duke heard: " + input;
    }
}