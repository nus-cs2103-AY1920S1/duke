import com.commands.*;
import com.exceptions.*;

import com.TaskList;

import com.util.Storage;
import com.util.Parser;
import com.util.Ui;

/**
 * A program named Duke.
 * Something like a Personal Assistant Chatbot that helps
 * a person keep track of various things, it can only
 * understand text commands of a specific structure.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
        parser = new Parser();
        ui = new Ui();
    }

    public void run() {
        ui.showGreetings();
        boolean doesProgramContinue = true;
        while (doesProgramContinue) {
            try {
                String userFullInput = ui.readUserInput();
                Command c = parser.parse(userFullInput);
                c.execute(this.getTaskList(), this.getStorage());
                doesProgramContinue = c.continuesProgram();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("F:\\CS2103\\duke\\data\\duke.txt").run();
    }

    //////////////
    // GETTERS //
    ////////////

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }

}