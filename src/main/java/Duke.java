import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.command.Command;
import duke.ui.TextUi;

public class Duke {
    private TextUi ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs an instance of Duke with a new TextUi, Storage and TaskList.
     */
    public Duke() {
        this.ui = new TextUi();
        this.storage = new Storage();
        this.taskList = storage.load();
    }

    public String getResponse(String input) {
        String nextCommand = input;
        Parser parser = new Parser();
        Command command = parser.parse(nextCommand);
        String rtn = command.execute(this.taskList);
        this.storage.update(this.taskList);
        return rtn;
    }

    public String greeting() {
        return this.ui.greeting();
    }

}