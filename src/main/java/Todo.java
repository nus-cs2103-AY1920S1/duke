public class Todo extends Task {
    /**
     * Constructor
     *
     * @param s description of task
     */
    public Todo(String s) {
        super(s);
    }

    /**
     * returns Task as formatted String
     *
     * @return formatted String
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
