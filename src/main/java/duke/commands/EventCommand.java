package duke.commands;

import duke.errands.Event;
import duke.errands.Task;
import duke.exception.DukeException;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class EventCommand extends Command {

    public EventCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        int position = this.input.indexOf("/");
        assert position != -1;
        if (position == -1) {
            throw new DukeException("☹ OOPS!!! Not a valid event command");
        }
        Task newTask = new Event(this.input.substring(0,position).trim(), this.input.substring(position + 3).trim());
        tasks.input(newTask);
        store.write(tasks);
        return ui.addTask(newTask, tasks);
    }

}
