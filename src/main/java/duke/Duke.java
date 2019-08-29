package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.util.Scanner;


/**
 * Driver class for Duke operations
 *
 * @author JXKENG
 */
public class Duke {
    public static final String filePath = "C:/Users/jxken/Desktop/Github/duke/data/duke.txt";

    /**
     * Starting point for duke commands and actions
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage(filePath);
        TaskList tasks = new TaskList(storage);
        UI ui = new UI(tasks, storage);

        ui.sayHi();

        boolean isExit = false;

        while (!isExit) {
            String fullCommand = sc.nextLine();
            try {
                UI.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            System.out.println("\t ☹ OOPS!!! " + e.getMessage());
        } finally {
            UI.printLine();
        }

        }


    }
}
