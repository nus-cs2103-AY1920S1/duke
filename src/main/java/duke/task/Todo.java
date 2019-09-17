package duke.task;

/**
 * This is a one kind of <code>Task</code> that specifies the description for the item in the task list.
 */
public class Todo extends Task {

    /**
     * {@inheritDoc}
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     *
     * @return a string representing this todo in the format of a tag "[T]" at the start, followed by the description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Compares two <code>Task</code> objects by their descriptions and <code>isDone</code> status.
     * The comparison is mainly used for JUnit tests.
     *
     * @param obj  the object to be compared
     * @return     <code>true</code> if the specifications for two tasks are all the same;
     *             <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        //@@author ZhangHuafan-reused
        //Reused from https://www.javaworld.com/article/3305792/comparing-java-objects-with-equals-and-hashcode.html
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        //@@author
        Todo another = (Todo) obj;
        boolean isSameDescription = this.description.equals(another.description);
        boolean isSameStatus = this.isDone == another.isDone;
        return isSameDescription && isSameStatus;
    }
}
