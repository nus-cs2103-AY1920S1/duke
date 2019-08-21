package command;

import java.util.Optional;

public interface Command {
    public Optional<Command> execute();
}
