package dukepkg;

public class Time extends dukepkg.Task{
    private String duration = "";
    /**
     * Instantiates a new Time Task.
     *
     * @param task the content used to construct the task.
     */
    public Time (String task, String duration) {
        super(task);
        this.duration = duration;
    }
    /**
     * Gets the duration within which the task must be done.
     *
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " (for: " + this.duration + ")";
    }

}
