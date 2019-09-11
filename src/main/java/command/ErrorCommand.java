package command;


/**
 *
 */

public class ErrorCommand extends Command {
    Exception err;

    /**
     *
     */

    public ErrorCommand(Exception x) {
        err = x;
    }

    /**
     *
     */


    public String executeCommand() {
        return this.formatOutput();
    }

    /**
     *
     */

    public String formatOutput() {
        return TextFormatter.errorFormat(err);
    }


}
