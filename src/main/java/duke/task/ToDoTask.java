package duke.task;

public class ToDoTask extends Task {

    /**
     * Constructs a ToDo Task which sets the default of isCompleted value to false.
     * @param todo The name of the task.
     */
    public ToDoTask(String todo) {
        super(todo);
    }

    /**
     * Constructs a ToDo Task based on the name and the isComplated value given.
     * @param todo The name of the task.
     * @param isCompleted Whether the task is Completed or not.
     */
    public ToDoTask(String todo, boolean isCompleted) {
        super(todo, isCompleted);
    }

    /**
     * Returns a string representation of the Task.
     * @return A string representation of the Task.
     */
    public String toString() {
        if (isCompleted) {
            return  String.format("[T][Y] %s", this.todo);
        } else {
            return  String.format("[T][N] %s", this.todo);
        }
    }

}
