package command;

/**
 *
 */

public class ListCommand extends Command {

    /**
     *
     */

    public String formatOutput() {

        return TextFormatter.listFormat(reference.getList());
    }

}
