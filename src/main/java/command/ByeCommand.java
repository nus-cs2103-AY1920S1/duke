package command;

import util.DukeInput;
import util.DukeMessage;
import util.DukeOutput;

import java.util.Optional;

public class ByeCommand implements Command {

    @Override
    public Optional<Command> execute() {
        DukeMessage goodbyeMessage = new DukeMessage("Bye. Hope to see you again soon!");
        DukeOutput.printMessage(goodbyeMessage);

        DukeInput.close();
        return Optional.empty();
    }
}
