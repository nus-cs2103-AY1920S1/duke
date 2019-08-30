/**
 * Represents a deletion command.
 */
public class Delete extends Command {

    public int number;

    /**
     * Initiates a Deletion object.
     * @param number the number of task to be deleted
     */
    public Delete(int number) {
        this.number = number;
    }
}

