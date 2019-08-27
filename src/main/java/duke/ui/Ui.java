package duke.ui;

import java.util.Scanner;

public class Ui {
    private static Ui ui = null;
    private Scanner sc;

    private Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the singleton UI instance.
     * 
     * @see <a href=
     *      "https://nus-cs2103-ay1920s1.github.io/website/se-book-adapted/chapters/designPatterns.html#singleton-pattern">
     *      Textbook reference on Singleton Object</a>
     * 
     * @return the UI instance to perform UI operations.
     */
    public static Ui getInstance() {
        if (ui == null) {
            ui = new Ui();
        }
        return ui;
    }

    /**
     * Returns the next line inputted by the user.
     * 
     * @return the next line inputted by the user.
     */
    public String getNextLine() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        }

        throw new IllegalStateException();
    }

    /**
     * Prints the given object to the user.
     * 
     * @param output the object to be printed.
     */
    public void printLine(Object output) {
        System.out.println(output);
    }
}