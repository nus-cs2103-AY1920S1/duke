public class Todo extends Task {

    /**
     * constructor.
     * @param description of task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    String getStoredForm() {
        String name = this.getDescription();
        int status = this.isDone()? 1 : 0;
        return "T " + status + " " + name + "\n";
    }

    /**
     * postpone time of the event.
     *
     * @param time is when the task is postponed to.
     */
    @Override
    void postpone(String time) throws NoPostponeException {
        throw new NoPostponeException();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
