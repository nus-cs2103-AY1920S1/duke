/**
 * Represents the Todo object
 * One Todo object per Todo.
 */
public class Todo extends Task{

    public Todo (String todoName) {
        super(todoName);
    }


    @Override
    public char getRepLetter() {
        return 'T';
    }
}
