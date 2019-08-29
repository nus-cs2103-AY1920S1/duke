package duke.ui;

/**
 * An interface that interacts with the Duke Program that can read inputs and print outputs.
 */
public interface Ui {
    public String readCommand();

    public void showWelcome();

    /**
     * Prints an output to the console.
     *
     * @param lines An array of messages to be wrapped.
     */
    public void print(String...lines);
}
