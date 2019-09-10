package duke.task;

public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    public Todo(String desc, boolean done) {
        super(desc, done);
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
     * @return Todo object
     */
    public static Todo parseFromData(String input) {
        String[] inputs = input.split(gap);
        return new Todo(inputs[2]);
    }

    /**
     *  Parses stored data string and look for Todo object.
     *
     * @param input Data string from save file.
     * @param isDone Whether todo task is done.
     * @return Todo object
     */
    public static Todo parseFromData(String input, boolean isDone) {
        Todo todo = parseFromData(input);
        todo.setDone(isDone);
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
