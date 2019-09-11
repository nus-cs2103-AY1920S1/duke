package command;


/**
 *
 */

public class HelloCommand extends Command {

    /**
     *
     */

    public String executeCommand() {
        return this.formatOutput();
    }

    public String formatOutput() {
        return TextFormatter.helloFormat();
    }

}


