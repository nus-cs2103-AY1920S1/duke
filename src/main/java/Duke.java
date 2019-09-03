<<<<<<< Updated upstream
public class Duke {
=======
import duke.command.Command;
import duke.util.*;

import java.io.FileNotFoundException;

/**
 * This is the driver class that utilises existing classes to receive and respond to the user's commands. It loads and
 * shows the task list from previous storage at the beginning. Util the user enters "bye", it responds to users' input
 * and modifies the current task list. It also informs user about invalid commands from input during the interaction.
 * When the user enters "bye" command, it stores the current task list and terminates the process.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * This is a sole constructor specifying the file path to load data from and write data to. It initialises a
     * <code>Ui</code>  to help with input and output, and a <code>Storage</code> object to loading and storing data.
     *
     * @param filePath a string specifying the path of the file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    /**
     * Starts the program by initialising and run the <code>Duke</code> object. The file path is
     * "/Users/xiaoyu/duke/data/duke.txt" by default.
     *
     * @param args an array of <code>String</code> read from console
     */
>>>>>>> Stashed changes
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
