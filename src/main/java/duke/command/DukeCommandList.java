package duke.command;

import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.DukeUi;

public class DukeCommandList extends DukeCommand {

    /**
     * This method will list the current existing {@link duke.task.DukeTask} from {@link DukeTaskList}.
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUi} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        tasks.displayDukeTasks(ui);
    }
}
