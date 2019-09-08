/**
 * Represents a deletion command.
 */
public class Delete extends Command {

    public Integer[] numbers;
    public String type;

    /**
     * Initiates a Deletion object.
     * @param numbers the numbers of task to be deleted
     */
    public Delete(Integer... numbers) {
        this.numbers = numbers;
    }

}

