/**
 * task class to manage the functions of tasks that are common across all types
 */
public class Task {
    boolean done = false;
    String content;
    int order;

    /**
     * task constructor
     * 
     * @param content the description of the task
     * @param status  whether the task is done or not
     * @param order   the order of the task in the list
     */
    public Task(String content, int status, int order) {
        this.content = content;
        done = status == 1 ? true : false;
        this.order = order;
    }

    public String toString() {
        String res = "[";
        if (done) {
            res += "\u2713";
        } else {
            res += "\u2718";
        }
        res += "] " + content;
        return res;
    }

    public void setAsDone() {
        done = true;
    }

    public String toFile() {
        int d;
        if (done) {
            d = 1;
        } else {
            d = 0;
        }
        return d + "," + content;
    }

    @Override
    /**
     * equality comparison of task (used for testing)
     */
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Task)) {
            return false;
        }
        Task t = (Task) o;
        return content.equals(t.content) && done == t.done;
    }
}