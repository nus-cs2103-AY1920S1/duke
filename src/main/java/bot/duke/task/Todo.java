package bot.duke.task;

/**
 * Represents the duke.Duke.Task.Todo object.
 * One duke.Duke.Task.Todo object per duke.Duke.Task.Todo.
 */
public class Todo extends Task {


    public Todo(String todoName) {
        super(todoName);
    }

    /**
     * Returns the Representing Letter to distinguish the Task types.
     *
     * @return Representing Letter
     */
    @Override
    public char getRepLetter() {
        return 'T';
    }

}
