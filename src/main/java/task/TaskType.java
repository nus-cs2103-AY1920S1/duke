package task;

/**
 * TaskType Enum.
 *
 * <p>Represents the type of a Task.
 *
 * @author Marcus Ong
 */
public enum TaskType {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");


    private final String tag;

    TaskType(String tag) {
        this.tag = tag;
    }


    public String getTag() {
        return tag;
    }

    public String toString() {
        return getTag();
    }
}
