public class Todo extends Task {

    /**
     * @param task name of a given Todo object
     *
     */

    public Todo(String task) {
        super(task);
    }

    /**
     * Returns the message comprising [T], name of the task of the Todo object,
     * together with the a symbol representing if a Todo object is completed or not.
     * [T] represents that our object is a Todo object.
     *
     */

    public String toString() {
        String box;
        String msg = "[T]";

        if (!this.isDone()) {
            box = "[✗] ";
        } else {
            box = "[✓] ";
        }
        return msg + box + this.getTask();
    }

    /**
     * Returns a message by Duke, when Duke has added a Todo object.
     *
     * @param size Number of tasks managed by Duke after we have added a Todo object.
     *
     */

    public String addedTodoMsg(int size) {
        String msg = "Got it. I've added this task: \n";
        msg = msg + "  " + this.toString() + "\n";
        String end = " tasks in the list.";

        if (size == 1) {
            end = " task in the list.";
        }
        return msg + " Now you have " + size + end;
    }


}


