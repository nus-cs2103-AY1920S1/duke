package jermi.type;

/**
 * Available types of task.
 */
public enum TaskType {
    /** todo task. */
    TO_DO("T"),
    /** deadline task. */
    DEADLINE("D"),
    /** event task. */
    EVENT("E");

    /** A unique letter to represent the task type. */
    private final String typeCode;

    /** Constructor for class.
     *
     * @param typeCode Task type code.
     */
    TaskType(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * Returns the task type code.
     *
     * @return Task type code.
     */
    public String getTypeCode() {
        return this.typeCode;
    }
}
