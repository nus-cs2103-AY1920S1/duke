public class Task {
    private String task;
    private boolean status = false;
    public Task(String task){
        this.task = task;
    }
    public void markAsDone() {
        this.status = true;
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "     " + this + "\n" +
                "    ____________________________________________________________\n");
    }
    @Override
    public String toString() {
        String logo;
        if (this.status == false) {
            logo = "✗";
        }else {
            logo = "✓";
        }
        return "[" + logo + "] " + this.task;
    }
}
