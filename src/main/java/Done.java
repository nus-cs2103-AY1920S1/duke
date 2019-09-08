/**
 * Represents a task completion command.
 */
public class Done extends Command {

    public Integer[] numbers;
    public String type;

    /**
     * Initiates a Done object.
     * @param numbers the number of task to be marked as done
     */
    public Done(Integer... numbers) {
        this.numbers = numbers;
    }

}
