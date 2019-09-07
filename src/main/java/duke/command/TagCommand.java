package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingDescriptionException;
import duke.task.Task;

public class TagCommand extends Command {
    int index;
    String[] tags;

    /**
     * Constructor for <code>TagCommand</code>.
     * @param index Index of task to be tagged.
     * @param tags Input tags by the user.
     */
    public TagCommand(int index, String[] tags) {
        super();
        this.index = index;
        this.tags = tags;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException,
            MissingDescriptionException {
        boolean isOutOfBounds = index >= tasks.getListSize();
        if (isOutOfBounds) {
            throw new InvalidIndexException();
        }
        tag(tasks, ui, storage);
    }

    private void tag(TaskList tasks, Ui ui, Storage storage) throws MissingDescriptionException {
        try {
            Task action = tasks.getTask(index);
            boolean hasNoTag = tags.length == 1;
            if (hasNoTag) {
                throw new MissingDescriptionException("tag");
            }
            for (int i = 1; i < tags.length; i++) {
                boolean isEmptyTag = tags[i].trim().length() == 0;
                if (isEmptyTag) {
                    continue;
                }
                action.addTag(tags[i]);
            }
            ui.printTagMessage(action, tags);
            storage.writeToHardDisk(tasks);
        } catch (DukeException exception) {
            ui.printException(exception);
        }
    }
}
