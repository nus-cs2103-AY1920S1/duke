package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.text.ParseException;

/**
 * Represents a deletion command.
 */
public class Delete extends Command {

    private Integer[] numbers;
    public String type;

    /**
     * Initiates a Deletion object.
     * @param numbers the numbers of task to be deleted
     */
    public Delete(Integer... numbers) {
        this.numbers = numbers;
    }

    @Override
    public String exec(Storage storage, TaskList tasks, Ui ui) {
        try {
            String response =  ui.delete(tasks, numbers);
            tasks.delete(numbers);
            storage.write(tasks);
            return response;
        } catch (DukeException ex) {
            return ui.showDukeException(ex);
        } catch (ParseException e) {
            return ui.showParseError();
        }
    }
}

