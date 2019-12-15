package duke.task;

/**
 * This class represents Event tasks which is a type of Task.
 */
public class Event extends Task {
    DateTime at;

    /**
     * Constructor for Events.
     * @param description Event description
     * @param at String representation of date of event
     */
    public Event(String description, String at) {
        super(description);
        this.typeOfTask = "[E]";
        this.at = new DateTime(at);
    }

    public void setAt(String at) {
        this.at = new DateTime(at);
    }

    public void setDescription(String description) {
        this.description  = description;
    }

    /**
     * Updates a particular task.
     * @param updatedContents String specifying contents to be updated with
     */
    public void update(String updatedContents) {
        String[] taskContentsList = updatedContents.split(" /at ");
        setDescription(taskContentsList[0]);
        setAt(taskContentsList[1]);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at.toString() + ")";
    }

    @Override
    public String writeToFile() {
        return super.writeToFile() + "|" + at.getRawForm();
    }
}
