package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.text.SimpleDateFormat;

public class Duke {
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HHmm");

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Duke instance.
     */
    public Duke() {
        this.storage = new Storage();
        this.tasks = storage.load();
    }

    /**
     * Processes a command.
     *
     * @param input Command string.
     * @return Output string.
     */
    public String process(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.executeCommand(this.tasks, this.storage);

            this.storage.persist(this.tasks);
            return output;
        } catch (DukeException e) {
            return Ui.showError(e);
        }
    }
}
