public class Task {

    // all class variables private

    private String taskInfo;
    private boolean isDone;
    private String taskType;

    public Task(String info, String type) {
        /*
        description: Constructor for duke, greets user.
        expects: No input
        outputs: returns nothing
         */
        this.taskInfo = info;
        this.isDone = false;
        this.taskType = type;
    }
    // getters
    public String getTaskInfo() {
        return taskInfo;
    }
    public String getStatus() {
        /*
        description: helper to print tick or cross
        in main duke class
        expects: No input
        outputs: returns tick if isDone true
        returns cross if isDone false
         */
        // taken from partial soln tq prof
        return (isDone ? "\u2713" : "\u2718");
    }
    public String getType() {
        return taskType;
    }
    public String printTask() {
        String s = "[" + getType() + "]";
        s += "[" + getStatus() + "] ";
        s += getTaskInfo();
        return s;
    }
    // setters
    public void markDone() {
        /*
        description: helper method to mark this task
        as done
        expects: No input
        outputs: returns nothing, only changes this object
         */
        this.isDone = true;
    }


}
