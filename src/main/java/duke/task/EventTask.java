package duke.task;

/**
 * A Class that represents a Task in which the user wishes to complete at a certain time period.
 */
public class EventTask extends Task {

    public String time;

    /**
     * Constructs am Event Task which sets the default of isCompleted value to false.
     * @param todo The name of the event task.
     * @param time The time of the event task.
     */
    public EventTask(String todo, String time) {
        super(todo);
        this.time = time;
    }

    /**
     * Constructs an Event Task based on the name and the isComplated value given.
     * @param todo The name of the task.
     * @param isCompleted Whether the task is Completed or not.
     * @param time The time of the task.
     */
    public EventTask(String todo, boolean isCompleted, String time) {
        super(todo, isCompleted);
        this.time = time;
    }

    /**
     * Changes the task name to the new task time given.
     * @param newTaskDate the new task time given.
     */
    public void editTaskDate(String newTaskDate) {
        this.time =  newTaskDate;
    }

    /**
     * Returns a string representation of the Task.
     * @return A string representation of the Task.
     */
    public String toString() {
        if (isCompleted) {
            return  String.format("[E][Y] %s (at: %s)", this.todo, this.time);
        } else {
            return  String.format("[E][N] %s (at: %s)", this.todo, this.time);
        }
    }
}
