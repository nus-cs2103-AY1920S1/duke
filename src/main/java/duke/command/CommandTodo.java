package duke.command;

import static duke.command.Messages.MSG_MISSING_TASK;

import duke.exception.DukeException;
import duke.command.exception.MissingDescriptionException;
import duke.sheet.Sheet;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Encapsulates a command from user input String "todo".
 */
public class CommandTodo extends Command {

    public static final String COMMAND_WORD = "todo";

    public CommandTodo(String command) {
        super(command);
        super.type = "Todo: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        if (this.command.isBlank()) {
            throw new MissingDescriptionException(MSG_MISSING_TASK);
        }
        Task todoTask = new Todo(command.trim());
        sh.add(todoTask);
        ui.showAdd(todoTask.toString().trim(), sh.getNumOfTask());
        stor.save(sh.getList());
    }

}
