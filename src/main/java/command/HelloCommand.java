package command;

/**
 *
 */

public class HelloCommand extends Command {

    /**
     *
     */

    public String formatOutput() {
        return TextFormatter.helloFormat();
    }

    /**
     *
     */

    public void passToUI(String input) {
        printer.printHelloMessage(input);
    }
}


