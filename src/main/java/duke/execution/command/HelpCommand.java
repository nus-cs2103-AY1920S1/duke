package duke.execution.command;

import java.io.IOException;

import duke.execution.CompleteList;
import duke.execution.Storage;
import duke.execution.Ui;

public class HelpCommand extends Command {

    public HelpCommand(String action) {
        super(action);
    }

    @Override
    public String execute(CompleteList errands, Ui ui, Storage storage) throws IOException {
        assert errands != null;
        assert ui != null;
        assert storage != null;
        return ui.printHelp();
    }
}

