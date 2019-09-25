package duke;

import duke.command.Command;
import duke.command.DukeException;
import duke.data.DukeData;
import duke.data.TaskList;

import java.io.IOException;

/**
 * <h1>Duke</h1>
 * The Duke program allows users to manage and organise their
 * to-do, deadlines, and events all in one application.
 * Users can add their tasks, marking them as done, list the tasks
 * they have, and even delete them after they are done.
 */
public class Duke { // handles all input and output
    private DukeData myData;
    private TaskList myTasks;
    private Ui myUi;

    /**
     * Creates a Duke program.
     */
    public Duke() {
        this.myData = new DukeData();
        this.myUi = new Ui();
        try {
            this.myTasks = new TaskList(this.myData.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method runs the Duke program.
     */
    private void run() {
        this.myUi.showIntro();

        String userCommand;
        while (this.myUi.hasNextInput() &&
                !(userCommand = this.myUi.getCommand()).equals("bye")) {
            String output;
            try {
                Command cmd = Parser.parse(userCommand);
                assert cmd != null;
                output = Ui.addLines(cmd.execute(
                        this.myData, this.myUi, this.myTasks));
                System.out.println(output);
            } catch (IOException e) {
                System.err.println(Ui.addLines(e.getMessage()));
            } catch (DukeException e) {
                System.err.println(Ui.addLines(e.getMessage()));
            }
        }
        this.myUi.showFarewell();
    }

    /**
     * This method retrieves the data that Duke has stored fdr the user.
     * @return a DukeData representation of the data that has been stored
     */
    public DukeData getData() {
        return this.myData;
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
     * @param input the user's command input
     * @return a String representation of the
     *         Duke's response based on the user input
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            assert cmd != null;
            return cmd.execute(
                    this.myData, this.myUi, this.myTasks);
        } catch (DukeException | IOException e) {
            return Ui.addLines(e.getMessage());
        }
    }
}