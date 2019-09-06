package duke.commands;

import duke.DukeException;
import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.tasks.Task;

public class SpecifyCommand extends Command{

    private int position;
    private String specifedTime;

    public SpecifyCommand(int position, String specifedTime) {
        this.position = position;
        this.specifedTime = specifedTime;
    }

    @Override
    public String execute(TaskList tl, Ui ui) throws DukeException {
        Task specifiedEvent = tl.specifyEventSlot(position, specifedTime);
        return ui.showSpecifyMessage(specifiedEvent);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
