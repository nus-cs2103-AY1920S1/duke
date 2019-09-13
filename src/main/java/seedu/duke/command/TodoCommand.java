package seedu.duke.command;

import seedu.duke.core.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.task.Todo;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public class TodoCommand extends Command {

    public TodoCommand() {

    }

    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                          Storage statStorage) throws DukeException {

        stats.incrementTotalCommandsExecuted();
        if (fullCommand.length() < 5) {
            // fullCommand contains only the string "todo".
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        String description = Parser.getTodoDescription(fullCommand);

        Todo newTodo = new Todo(description);

        tasks.addTask(newTodo);

        return ui.getTodoSequence(tasks, newTodo);

    }

}
