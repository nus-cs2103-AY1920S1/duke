package command;

/**
 *
 */

public class ListCommand extends Command {

    /**
     *
     */

    public String formatOutput() {

        return textFormatter.listFormat(reference.getList());
    }

    /**
     *
     */

    public void passToUI(String input) {

        printer.printTasks(input);
    }
}
