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

    private static List<Task> taskList = new ArrayList<Task>();

    public Duke() {
        // Load from file
        try {
            taskList = Storage.getInstance().loadFromDisk();
        } catch (Storage.StorageOperationException e) {
            Ui.printError(e);
        }
    }

    /**
     * Main Method.
     *
     * @param args string arguments for console.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Ui.greet();



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
