package duke.command.update;

import duke.command.DukeCommandUpdate;
import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.DukeUi;

public class DukeCommandUpdateTaskDelete extends DukeCommandUpdate {

    /**
     * Constructor that takes in the user input split by the " " delimiter into a String[].
     * @param inputTokens User entered line split by a space delimiter.
     */
    public DukeCommandUpdateTaskDelete(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Marks the specified task from the user input as complete.
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUi} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        tasks.deleteDukeTask(inputTokens[1], ui, storage);
    }
}
