package command;
public class ByeCommand extends Command {

    public String formatOutput() {
        return textFormatter.byeFormat();
    }

    public void passToUI(String input) {

        printer.printByeMessage(input);
    }
}

