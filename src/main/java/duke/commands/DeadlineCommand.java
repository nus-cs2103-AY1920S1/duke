package duke.commands;

import duke.errands.Deadline;
import duke.errands.Task;
import duke.exception.DukeException;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        int position = this.input.indexOf("/");
        if (position == -1) {
            throw new DukeException("☹ OOPS!!! Not a valid deadline command");
        }
        String formattedDate = Parser.getFormattedDate(this.input.substring(position + 3));
        Task newTask = new Deadline(this.input.substring(0,position).trim(), formattedDate);
        tasks.input(newTask);
        store.write(tasks);
        return ui.addTask(newTask, tasks);
    }

}
