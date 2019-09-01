import ui.Duke;

/**
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 3
 */
public class Main {
    /**
     * The main method for ui.Duke.
     *
     * Creates a new instance of ui.Duke and runs it.
     *
     * @param args command(s) from the user
     */
    public static void main(String[] args) {
        // create an instance of ui.Duke with the file containing the task list
        new Duke("../../../data/tasks.txt").run();
    }
}