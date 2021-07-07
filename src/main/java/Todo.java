public class Todo extends Task {
    static String TODO_CMD = "todo";
    static String TODO_PREFIX = "[T]";

    /**
     * Constructor.
     *
     * @param s description of task
     */
    public Todo(String s) {
        super(s);
    }

    /**
     * Returns Task as formatted String.
     *
     * @return formatted String
     */
    public String toString() {
        return format(super.toString());
    }

    public String toFileString() {
        return format(super.toFileString());
    }


    private String format(String str) {
        String temp = str.replaceAll(TODO_CMD, " ");
        String result = super.cleanString(temp);
        return TODO_PREFIX + result;
    }





}
