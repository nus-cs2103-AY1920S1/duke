package command;

import util.dukeInput;
import util.dukeOutput;

import java.util.Optional;

public class EchoCommand implements Command {
    @Override
    public Optional<Command> execute() {
        String userInput = dukeInput.readUserInput();

        if (userInput.equals("bye")) {
            String goodbyeMessage = "Bye. Hope to see you again soon!";
            dukeOutput.printMessage(goodbyeMessage);

            dukeInput.close();
            return Optional.empty();
        } else {
            dukeOutput.printMessage(userInput);

            return Optional.of(new EchoCommand());
        }
    }
}
