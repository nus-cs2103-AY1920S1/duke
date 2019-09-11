// Adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html 

import java.util.Date;

/**
 * Class representation of "aftertasks" in the list.
 * An aftertask is a task which is to be done after a specific time. 
 */
public class AfterTask extends Task {
    protected Date after;

    /**
     * Creates a new AfterTask.
     * 
     * @param description Description of the AfterTask
     * @param after Date after which the AfterTask is to be done
     */
    public AfterTask(String description, Date after) {
        super(description);
        this.after = after;
    }

    /**
     * Returns a String representation of this AfterTask.
     * 
     * @return String representation of this AfterTask
     */
    @Override
    public String toString() {
        return "[A]" + super.toString() + " (after: " + after + ")";
    }

    /**
     * Gets the Date after which this AfterTask is to be done.
     * 
     * @return Date after which the AfterTask is to be done
     */
    public Date getAfter() {
        return this.after;
    }
}
