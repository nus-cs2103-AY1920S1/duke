package duke.priority;

/**
 * The Priority enumeration defines the behaviour of a priority.
 * 
 * @author Joel Loong
 */
public enum Priority {
    HIGH(1), MEDIUM(2), LOW(3);

    private int priorityLevel;

    private Priority(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getPriorityLevel() {
        return this.priorityLevel;
    }

    @Override
    public String toString() {
        return "(PRIORITY: " + this.name() + ")";
    }
}