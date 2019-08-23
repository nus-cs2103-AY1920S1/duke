package duke.command;

import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.DukeUi;

public class DukeCommandUpdate extends DukeCommand {

    private String[] inputTokens;

    /**
     * Constructor that takes in the user input split by the " " delimiter into a String[].
     * @param inputTokens User entered line split by a space delimiter.
     */
    public DukeCommandUpdate(String[] inputTokens) {
        this.inputTokens = inputTokens;
    }

    /**
     * This method will either update by marking a {@link duke.task.DukeTask} as done or deleting it, depending on the
     * command name.
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUi} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        if (inputTokens.length == 2) {
            if (inputTokens[0].equals("done")) {
                tasks.markDukeTaskComplete(inputTokens[1], ui, storage);
            } else if (inputTokens[0].equals("delete")) {
                tasks.deleteDukeTask(inputTokens[1], ui, storage);
            }
        } else {
            ui.displayMissingIndex();
        }
    }
}
