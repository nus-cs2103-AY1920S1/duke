package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class EventCommand extends AddCommand {
    public EventCommand(String desc) {
        super(desc);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event eventTask;

        try {
            String[] eventArr = getDesc().split("/at");
            eventTask = Event.of(eventArr[0], eventArr[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please follow correct format of \"event [description] /at [date]\".");
        }

        taskList.addTask(eventTask);
        return ui.getAddedTask(eventTask.toString(), taskList.getNumTasks());
    }
}
