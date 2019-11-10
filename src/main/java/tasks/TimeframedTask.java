package tasks;

/**
 * Class that represents tasks with a dateTime field.
 */
public abstract class TimeframedTask extends Task {

    public TimeframedTask(String des) {
        super(des);
    }

    /**
     * Updates dateTime to the new dateTime given.
     *
     * @param newDateTime updated dateTime
     */
    public abstract void setDateTime(String newDateTime);

}
