package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.text.ParseException;

public class HelpCommand extends Command {

    String toPrint;

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException {
        toPrint = "Commands\n" +
                "1. Add ToDos - todo [text]\n" +
                "2. Add Deadlines - deadline [text] + ' /' + [date DD/MM/YYYY] + ' ' + [time TTTT]\n" +
                "3. Add Event - event [text] + ' /' + [date DD/MM/YYYY] + ' ' + [time TTTT]\n" +
                "4. List tasks - list\n" +
                "5. Delete tasks - delete [index]\n" +
                "6. Marks task as done - done [index]\n" +
                "7. Find task through keywords - find [text]\n" +
                "8. Exit program - bye";
        return toPrint;
    }
}
