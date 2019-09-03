/**
 * Contains methods to interact with the user.
 */
public class Ui {

    private boolean isProcessing = true;

    public Ui() {

    }

    public boolean shouldContinue() {
        return isProcessing;
    }
    /**
     * Prints a hello message at the beginning of the program.
     */
    void Greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

    }

    /**
     * Prints a bye message when the user leaves.
     *
     */
    public void Exit() {
        System.out.println("Bye. Hope to see you again soon!");
        this.isProcessing = false;
    }

}
