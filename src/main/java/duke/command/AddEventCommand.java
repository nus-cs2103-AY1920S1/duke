package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Event;

import static duke.ui.Message.ADD_TASK_MESSAGE;

public class AddEventCommand extends Command {
    public AddEventCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert super.description != null;
        if (super.description.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        String content = description[1];
        String[] actionAndDate = content.split("/at");

        if (actionAndDate.length < 2) {
            throw new DukeException("☹ OOPS!!! The format for event is invalid.");
        }

        Event event = new Event(actionAndDate[0], actionAndDate[1]);
        tasks.addTask(event);

        storage.save(tasks);
        return ui.showTask(event, tasks, ADD_TASK_MESSAGE);
    }
}
