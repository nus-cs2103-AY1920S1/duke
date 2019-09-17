package duke;

import duke.command.Command;
import duke.command.DukeException;
import duke.data.DukeData;

import java.io.IOException;

/**
 * <h1>Duke</h1>
 * The Duke program allows users to manage and organise their to-do, deadlines, and events
 * all in one application. Users can add their tasks, marking them as done, list the tasks
 * they have, and even delete them after they are done.
 */
public class Duke { // handles all input and output
    private DukeData _myData;
    private Ui ui;

    /**
     * Creates a Duke program.
     */
    public Duke() {
        this._myData = new DukeData();
        this.ui = new Ui();
    }

    /**
     * Creates a Duke program with filePath as the path to save Duke's data.
     * @param filePath the path to save the Duke's data from user input
     */
    public Duke(String filePath) {
        this._myData = new DukeData(filePath);
        this.ui = new Ui();
    }

    /**
     * This method runs the Duke program.
     */
    private void run() {
        this.ui.showIntro();

        String userCommand;
        while (this.ui.hasNextInput() &&
                !(userCommand = this.ui.getCommand()).equals("bye")) {
            String output;
            try {
                Command cmd = Parser.parse(userCommand);
                assert cmd != null;
                output = Ui.addLines(cmd.execute(this._myData, this.ui));
                System.out.println(output);
            } catch (IOException e) {
                System.err.println(Ui.addLines(e.getMessage()));
            } catch (DukeException e) {
                System.err.println(Ui.addLines(e.getMessage()));
            }
        }
        this.ui.showFarewell();
    }

    /**
     * This method retrieves the data that Duke has stored fdr the user.
     * @return a DukeData representation of the data that has been stored
     */
    public DukeData getData() {
        return this._myData;
    }

    /**
     * This is the main method that initiates the running of the Duke program.
     * @param args Unused
     */
    public static void main(String[] args) { // handles all input and output
        Duke theDuke = new Duke();
        theDuke.run();
    }

    /**
     * This method generates a response to user input.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            assert cmd != null;
            return cmd.execute(this._myData, this.ui);
        } catch (DukeException | IOException e) {
            return Ui.addLines(e.getMessage());
        }
    }
}