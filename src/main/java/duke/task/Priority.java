package duke.task;

/**
 * Provides four possible Priority levels for Tasks: High, Medium, Low and None.
 * The String representations of Priority levels are, respectively:
 * "Important!!", "Quite important", "Not very important", and "None".
 */
public enum Priority {
    NONE("None", 0),
    LOW("Not very important", 1),
    MEDIUM("Quite important", 2),
    HIGH("Important!!", 3);

    private String descriptor;
    private int importance;

    private Priority(String descriptor, int importance) {
        this.descriptor = descriptor;
        this.importance = importance;
    }

    /**
     * Returns an integer representation of the Priority level. Integer
     * representations of priority levels range from 0 (NONE) to 3 (HIGH).
     *
     * @return Integer representing the level of Priority.
     */
    public int asInteger() {
        return this.importance;
    }

    /**
     * Returns a user-friendly String representation of a Priority.
     *
     * @return String containing a description of the current Priority.
     */
    @Override
    public String toString() {
        return this.descriptor;
    }
}
