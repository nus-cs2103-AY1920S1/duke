package duke.command;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

public class LoadCommand extends Command{
    private String filePath;

    public LoadCommand(String filePath) {
        super();
        this.filePath = filePath;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.setFilePath(filePath);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) {
            storage.setFilePath(filePath);
            try {
                storage.load();
                return ui.showLoadingSuccessGui();
            } catch (DukeException e) {
                return ui.showLoadingErrorGui();
            }
    }
}
