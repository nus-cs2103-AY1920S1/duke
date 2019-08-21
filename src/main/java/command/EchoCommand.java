package command;

import util.dukeOutput;

import java.util.Optional;
import java.util.Scanner;

public class EchoCommand implements Command {
    private static Scanner dukeScanner = new Scanner(System.in);

    @Override
    public Optional<Command> execute() {
        String userInput = dukeScanner.nextLine();

        if (userInput.equals("bye")) {
            String goodbyeMessage = "Bye. Hope to see you again soon!";
            dukeOutput.printMessage(goodbyeMessage);

            dukeScanner.close();
            return Optional.empty();
        } else {
            dukeOutput.printMessage(userInput);

            return Optional.of(new EchoCommand());
        }
    }
}
