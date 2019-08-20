public class Task {
    boolean done;
    int no;
    String task;

    public Task(int num, String task){
        this.no = num;
        this.done = false;
        this.task = task;
    }

    public void setDone() {
        this.done = true;
    }

    public String listify() {
        return "    " + no + ". [" + (done ? "✓" : "✗") + "] " + task;
    }

    public String doneify()  {
        return  "     Nice! I've marked this task as done: \n" +
                "       [✓] " + task;
    }

    @Override
    public String toString() {
        return "    added: " + task;
    }

}
