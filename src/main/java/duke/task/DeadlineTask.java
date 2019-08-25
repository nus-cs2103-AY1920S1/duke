package duke.task;

public class DeadlineTask extends Task{

    public String deadline;

    /**
     * Constructs a Deadline Task which sets the default of isCompleted value to false.
     * @param todo The name of the deadline task.
     */
    public DeadlineTask(String todo, String deadline) {
        super(todo);
        this.deadline = deadline;
    }

    /**
     * Constructs a Deadline Task based on the name and the isComplated value given.
     * @param todo The name of the task.
     * @param isCompleted Whether the task is Completed or not.
     */
    public DeadlineTask(String todo, boolean isCompleted, String deadline) {
        super(todo, isCompleted);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the Task.
     * @return A string representation of the Task.
     */
    public String toString() {
        if (isCompleted) {
            return  String.format("[D][Y] %s (by: %s)", this.todo, this.deadline);
        } else {
            return  String.format("[D][N] %s (by: %s)", this.todo, this.deadline);
        }
    }
}
