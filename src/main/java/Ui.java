import java.util.Scanner;

import duke.exception.DukeException;

/**
 * Represents the User-Interface commands.
 */
public class Ui {

    private Duke parent;

    public Ui(Duke parent) {
        this.parent = parent;
    }

    /**
     * Reads the inputs entered by the user.
     */
    public void readInputs() {
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {
            input = sc.nextLine();
            try {
                parent.evaluate(input);
            } catch (DukeException e) {
                dukeOutput(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Outputs the entered string, formatted with bounding lines.
     *
     * @param out The string to be outputted.
     */
    public void dukeOutput(String out) {
        String bound = "_______________________________________";
        String newOutput = out.replace("\n", "\n    ");
        System.out.println("    " + bound + "\n    "
                + newOutput + "\n    " + bound);
    }

}
