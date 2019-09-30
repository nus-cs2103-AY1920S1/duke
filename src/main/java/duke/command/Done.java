package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.text.ParseException;

/**
 * Represents a task completion command.
 */
public class Done extends Command {

    private Integer[] numbers;
    public String type;

    /**
     * Initiates a Done object.
     * @param numbers the number of task to be marked as done
     */
    public Done(Integer... numbers) {
        this.numbers = numbers;
    }

    @Override
    public String exec(Storage storage, TaskList tasks, Ui ui) {
        try {
            tasks.done(numbers);
            storage.write(tasks);
            return ui.done(tasks, numbers);
        } catch (DukeException ex) {
            return ui.showDukeException(ex);
        } catch (ParseException e) {
            return ui.showParseError();
        }
    }
}
