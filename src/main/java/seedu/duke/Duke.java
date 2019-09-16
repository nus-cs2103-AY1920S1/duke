package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.helpers.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.trackables.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static final String HORIZONTAL_LINE = "______________________________"
        + "______________________________";

    private static List<Task> taskList = new ArrayList<Task>();

    /**
     * Main Method.
     *
     * @param args string arguments for console.
     */
    public static void main(String[] args) {

        Ui.greet();

        // Load from file
        try {
            taskList = Storage.getInstance().loadFromDisk();
        } catch (Storage.StorageOperationException e) {
            Ui.printError(e);
        }

        Scanner in = new Scanner(System.in);

        //noinspection InfiniteLoopStatement
        while (true) {
            String input = in.nextLine();
            try {
                Parser.parseCommand(input).execute(taskList);

            } catch (DukeException e) {
                Ui.printError(e);
            }
        }
    }


}
