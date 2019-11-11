package duke.task;

public class Todo extends Task {

    /**
     * Initialise a new Task.
     *
     * @param desc Description of the Task.
     * @param done Whether the task is done.
     */
    public Todo(String desc, boolean... done) {
        super(desc);
        if (done.length == 1) {
            setDone(done[0]);
        }
    }

    /**
     * Parses user input and look for Todo object.
     *
     * @param commandContent Input string
     * @return Todo object
     */
    public static Todo parse(String commandContent) {
        return new Todo(commandContent);
    }

    /**
     *  Parses stored data string and look for Todo object.
     *
     * @param input Data string from save file.
     * @param done Optional isDone input
     * @return Todo object
     */
    public static Todo parseFromData(String input, boolean... done) {
        String[] inputs = input.split(gap);
        Todo todo = new Todo(inputs[2]);
        if (done.length == 1) {
            todo.setDone(done[0]);
        }
        return todo;
    }

    @Override
    public String toStorageString() {
        return "T" + gap + super.toStorageString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
