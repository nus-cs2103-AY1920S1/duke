import java.io.Serializable;

/**
 * Represents a Task.
 */
class Task implements Serializable {
    // state constants
    static final char DONE = '\u2713';
    static final char NOT_DONE = '\u2717';

    private char state; // dont use boolean
    private String name;

    /**
     * Create new Task with description
     * @param name
     */
    Task (String name){
        this.name = name;
        this.state = NOT_DONE;
    }

    /**
     * Create a todo task with description
     * @param name description
     * @return todo Task
     * @throws DukeException
     */
    public static Todo parseTodo(String name) throws DukeException {
        if(name.isEmpty()){
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(name);
    }

    /**
     * Create a deadline task with description and date
     * @param name description
     * @param date date
     * @return deadline task
     * @throws DukeException
     */
    public static Deadline parseDeadline(String name, String date) throws DukeException {
        if(name.isEmpty()){
            throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
        } else if(date.isEmpty()){
            throw new DukeException("\u2639 OOPS!!! The date of a deadline cannot be empty.");
        }
        return new Deadline(name, date);
    }

    /**
     * Create a event task with a description and date
     * @param name description
     * @param date date
     * @return event task
     * @throws DukeException
     */
    public static Event parseEvent(String name, String date) throws DukeException {
        if(name.isEmpty()){
            throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
        } else if(date.isEmpty()){
            throw new DukeException("\u2639 OOPS!!! The date of an event cannot be empty.");
        }
        return new Event(name, date);
    }

    /**
     * Sets state of task
     * @param state
     */
    public void setState(char state) {
        this.state = state;
    }

    /**
     * Get state of Task
     * @return state
     */
    public char getState() {
        return state;
    }

    /**
     * Get name of Task
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + state + "] " + name;
    }
}
