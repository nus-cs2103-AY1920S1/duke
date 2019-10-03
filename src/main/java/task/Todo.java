package task;

import exceptions.MissingInputException;

public class Todo extends Task {

    /**
     * Creates Todo (also a Task).
     *
     * @param num Number in the list.
     * @param task String indicates description of task.
     * @param type String indicates task type, in this case "T".
     * @param done boolean indicates whether the task is done.
     */
    public Todo(int num, String task, String type, boolean done) throws MissingInputException {
        super(num, task, type, done);
    }

    /**
     * Creates Todo (also a Task).
     *
     * @param num Number in the list.
     * @param task String indicates description of task.
     * @param type String indicates task type, in this case "T".

     */
    public Todo(int num, String task, String type) throws MissingInputException {
        super(num, task, type);
    }

    /**
     * Formats the todo for printing.
     *
     * @return String in the form for printing
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", done ? "Y" : "N",task);
    }

    /**
     * Formats task to be written in given file.
     *
     * @return formatted string for writing in file.
     */
    @Override
    public String fileFormat() {
        return String.format("T | %s | %s", done ? "1" : "0", task);
    }
}
