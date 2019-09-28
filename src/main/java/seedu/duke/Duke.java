package seedu.duke;

import java.util.Scanner;
import seedu.duke.command.Command;
import seedu.duke.task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Create a new Duke instance.
     * 
     * @param filePath the path to the data file to save sata in.
     */
    public Duke(String filePath) {
        ui = new Ui(new Scanner(System.in));
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                ui.show(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
                continue;
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Given input, gets response as string.
     * 
     * @param input input as multiline string
     * @return A multiline string output
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Main function that runs the cli.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            // gui by default
            new Duke("data/tasks.txt").run();
        }
    }
}
