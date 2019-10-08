package duke.command.bye;

import duke.command.Command;
import duke.command.CommandProducer;
import error.command.CommandCreationException;
import ui.Ui;

public class ByeCommandProducer extends CommandProducer {
    private Ui ui;

    public ByeCommandProducer(Ui ui) {
        super("bye");
        this.ui = ui;
    }

    @Override
    public Command generateCommand(String arguments) throws CommandCreationException {
        if (!arguments.equals("")) {
            throw new CommandCreationException("Accept arguments, this command does not.");
        }

        return new ByeCommand(ui);
    }
}
