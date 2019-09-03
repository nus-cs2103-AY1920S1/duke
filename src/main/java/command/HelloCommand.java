package command;
public class HelloCommand extends Command {

    public String formatOutput() {
        return textFormatter.helloFormat();
    }

    public void passToUI(String input) {

        printer.printHelloMessage(input);
    }
}


