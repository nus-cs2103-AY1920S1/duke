package command;


/**
 *
 */

public class ByeCommand extends Command {

    /**
     *
     */

    public String formatOutput() {
        return TextFormatter.byeFormat();
    }

    /**
     *
     */


    public String executeCommand() {
        return this.formatOutput();
    }

}

