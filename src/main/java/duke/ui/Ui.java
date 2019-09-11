package duke.ui;

import duke.exception.DukeDuplicateTaskException;
import duke.io.Parser;
import duke.io.Storage;
import duke.exception.DukeIllegalActionException;
import duke.exception.DukeIllegalDescriptionException;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Generates and prints the greeting message of DukeBot.
     */
    public String initMessage() {
          return ("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private Storage storage;

    /**
     *
     * @param storage storage object to interact with
     */
    public Ui(Storage storage) {
        this.storage = storage;
    }

    static Scanner sc = new Scanner(System.in);

    public static Scanner getScanner() {
        return sc;
    }

    public void showLoadingError() {
        System.out.println("Task list not retrieved.");
    }

    /**
     * Reads user input from console and passes to parser to react.
     */
    public String readUserInput(String act) {
        try {
            return Parser.parse(act, storage);
        } catch (DukeIllegalDescriptionException | DukeIllegalActionException | DukeDuplicateTaskException e) {
            return (e.getMessage());
        } catch (FileNotFoundException e) {
            return "File not found.";
        }
    }
}
