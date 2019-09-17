import com.commands.*;
import com.exceptions.*;

import com.TaskList;

import com.util.Storage;
import com.util.Parser;
import com.util.Ui;
import com.util.stats.StatsStorage;

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

    public Duke() throws DukeException {
        storage = new Storage("F:\\CS2103\\duke\\data\\duke.txt");
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
                assert doesProgramContinue == true : "doesProgramContinue should be true";
                doesProgramContinue = c.continuesProgram();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
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