package duke.task;

/**
 * Represents the Duke.Task.Todo object
 * One Duke.Task.Todo object per Duke.Task.Todo.
 */
public class Todo extends Task {

    public Todo(String todoName) {
        super(todoName);
    }


    @Override
    public char getRepLetter() {
        return 'T';
    }

}
