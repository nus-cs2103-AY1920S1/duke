public class FixedDurationTask extends Task {
    private String duration;
    /**
     * Constructs a Todo task object.
     * @param desc description of the task by user
     * @throws DukeException if task description is not inputted
     */
    public FixedDurationTask(String desc, String duration) {
        super(desc);
        this.duration = duration;
    }


    /**
     * Converts the object to its string form to be printed.
     * @return String printing out the Todo object's description
     *          and status of completion
     */
    @Override
    public String toString() {
        return "[F]" + super.toString() + " (needs: " + this.duration + ")";
    }

    /**
     * Converts the Todo object to the String form to be saved in file.
     * @return String format of the object
     */
    public String toFileFormat() {
        if (isDone) {
            return "F | [✓] | " + taskDesc + " | " + duration + "\n";
        } else {
            return "F | [✗] | " + taskDesc + " | " + duration + "\n";
        }
    }
}
