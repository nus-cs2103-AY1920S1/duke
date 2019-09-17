public class ToDo extends Task {

    /**
     * Constructor for todo
     * @param desc description of the todo
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * To represent the todo
     * @return a String representing the todo
     */

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
