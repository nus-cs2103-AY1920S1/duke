/**
 * Represents an addition command.
 */
public class Add extends Command {

    public String type;
    public String description;

    /**
     * Initiates an Add object.
     * @param type type of new task
     * @param description description of the new task
     */
    public Add(String type, String description) {
        this.type = type;
        this.description = description;
    }
}
