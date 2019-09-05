package duke;

import duke.command.Command;

/**
 * Driver class
 */

public class Duke {
    public static boolean isExiting = false;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        // Greet user
        ui.showWelcomeMessage();
        ui.showInitialListMessage(tasks);

        // Ask initial user input
        while (!isExiting) {
            String userinput = ui.readCommand();
            Command userCommand = Parser.parse(userinput);

            try {
                userCommand.execute(tasks);
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }

        // At this point userinput equals "bye"
        // Save data to file
        storage.save(tasks);
        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke("../data/duke.txt").run();
    }
}
