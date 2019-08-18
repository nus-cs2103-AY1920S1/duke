public class Task {
    private String taskInfo;
    boolean isDone;
    public Task(String info) {
        /*
        description: Constructor for duke, greets user.
        expects: No input
        outputs: returns nothing
         */
        this.taskInfo = info;
        this.isDone = false;
    }
    private String getStatus() {
        /*
        description: helper to print tick or cross
        in main duke class
        expects: No input
        outputs: returns tick if isDone true
        returns cross if isDone false
         */
        return (isDone ? "\u2713" : "\u2718"); // taken from partial soln tq prof
    }
    private void markDone() {
        /*
        description: helper method to mark this task
        as done
        expects: No input
        outputs: returns nothing, only changes this object
         */
        this.isDone = false;
    }


}
