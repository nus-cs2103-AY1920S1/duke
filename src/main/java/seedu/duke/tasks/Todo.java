//Solution below adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html
package seedu.duke.tasks;

/**
 * Task that includes a description and a date and time for the event.
 */
public class Todo extends Task {

    /**
     * Constructor that specifies the description of the task.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + " " + super.toString();
    }
}