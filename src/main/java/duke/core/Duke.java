package duke.core;

import duke.command.Command;


/**
 * This program reads in command, adds new tasks into the task lists, changes the task
 * status when it is done, and delete the task according to the command.
 */

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Loads the data from given file path.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns a string representation of duke's response to the DialogBox.
     * @param input String of user input.
     * @return String of duke's response to be shown.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return ui.sayBye();
        } else {
            try {
                Parser parser = new Parser(input, tasks.getNumOfTask());
                Command command = parser.parseCommand();
                return command.execute(tasks, storage);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }

    /**
     * Returns a String of greeting message to the DialogBox.
     * @return String of greeting message from duke.
     */
    public String getGreeting() {
        return ui.greet();
    }
}