package duke;

import duke.core.Storage;
import duke.core.Ui;
import duke.core.Parser;
import duke.command.Command;
import duke.helper.DukeException;
import duke.task.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui userIF;

    /**
     * Constructor for Duke which is called in main. Creates a new Ui and Storage. If Storage can't find the file
     * duke.txt it will throw an exception, in which case a new TaskList will be created that is empty.
     */
    public Duke() {
        this.userIF = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.outputFileContents());
        } catch (DukeException e) {
            e.getMessage();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            //no empty string input since already disabled send button if is empty string
            assert input != "";
            Command c = Parser.parse(input);
            return c.execute(tasks, userIF, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String sayHello() {
        return userIF.printHello();
    }

}
