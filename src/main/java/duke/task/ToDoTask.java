package duke.task;

/**
 * A Class that represents a Task in which the user wishes to do.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a ToDo Task which sets the default of isCompleted value to false.
     * @param todo The name of the task.
     */
    public ToDoTask(String todo) {
        super(todo);
    }

    /**
     * Constructs the name of the todo task.
     * @param todo the array containing the data for the task name.
     * @return the task name as a String.
     */
    public static String getName(String[] todo) {
        String taskName = "";
        for (int i = 1; i < todo.length; i++) {
            if (i > 1) {
                taskName += " ";
            }
            taskName += todo[i];
        }
        return taskName;
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
