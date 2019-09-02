package duke;

import duke.command.Command;
import duke.ui.Main;
import javafx.application.Application;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Create a new Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        taskList = new TaskList(storage.load());
    }

    /**
     * Start the program execution.
     */
    /*
    public void run() {
        boolean shouldContinue = true;

        while (shouldContinue) { //Read input until 'bye' command is entered
            String input = ui.readCommand();
            Command command = Parser.parse(input); // Parse and perform logic
            command.execute(ui, storage, taskList);
            shouldContinue = command.shouldContinue();
        }
        ui.printExit();
    }
     */

    public String getResponse(String input) {
        Command command = Parser.parse(input); // Parse and perform logic
        return command.execute(ui, storage, taskList);
    }

    public static void main(String[] args) {
        /*
        Duke duke = new Duke();
        duke.run();
         */
        Application.launch(Main.class, args);
    }
}
