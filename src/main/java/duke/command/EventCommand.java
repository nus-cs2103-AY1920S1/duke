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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String[] eventArr = getDesc().split("/at");
            Event eventTask = Event.of(eventArr[0], eventArr[1]);
            taskList.addTask(eventTask);
//            storage.store(eventTask);
            ui.showAddedTask(eventTask.toString(), taskList.getNumTasks());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please follow correct format of \"event [description] /at [date]\".");
        }
    }
}
