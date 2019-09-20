package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ChangeSaveFileCommand extends Command {
    /**
     * Constructs a change save file command.
     *
     * @param params String containing new save file path.
     */
    public ChangeSaveFileCommand(String params) {
        super(params);
    }

    /**
     * Executes the command.
     *
     * @param tasks   Task list containing all tasks.
     * @param storage Storage instance.
     * @return Success message.
     */
    @Override
    public String executeCommand(TaskList tasks, Storage storage) {
        storage.setPath(this.params);
        tasks.importFrom(storage);

        System.out.println(tasks.toString());

        return Ui.showChangedSaveFile(this.params);
    }
}
