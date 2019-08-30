/**
 * Represents a task completion command.
 */
public class Done extends Command {

    public int number;

    /**
     * Initiates a Done object.
     * @param number the number of task to be marked as done
     */
    public Done(int number) {
        this.number = number;
    }
}
