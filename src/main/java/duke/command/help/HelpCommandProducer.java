package duke.command.help;

import duke.command.Command;
import duke.command.CommandProducer;
import error.command.CommandCreationException;
import ui.Ui;

public class HelpCommandProducer extends CommandProducer {
    private Ui ui;

    public HelpCommandProducer(Ui ui) {
        super("help");
        this.ui = ui;
    }

    @Override
    public Command generateCommand(String arguments) throws CommandCreationException {
        return new HelpCommand(ui);
    }
}
