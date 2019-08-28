/**
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 3
 */
public class Main {
    /**
     * The main method for Duke.
     *
     * Creates a new instance of Duke and runs it.
     *
     * @param args command(s) from the user
     */
    public static void main(String[] args) {
        // create an instance of Duke with the file containing the task list
        new Duke("../../../data/tasks.txt").run();
    }
}