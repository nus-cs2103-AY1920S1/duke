package duke;

import duke.util.cli.CliDuke;

public interface Duke {

    /**
     * Runs Duke.
     */
    void run();

    /**
     * Creates and runs a new instance of Duke.
     * Invoked when Duke is run from the CLI.
     * @param args Arguments supplied by the user.
     */
    static void main(String[] args) {
        // running Duke from the CLI should instantiate CliDuke, as usual
        Duke duke = new CliDuke();
        duke.run();
    }
}
