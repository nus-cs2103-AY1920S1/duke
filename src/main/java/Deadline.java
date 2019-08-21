public class Deadline extends Task {

    String time;

    public Deadline(int num, String task, String time) {
        super(num, task);
        this.time = time;
    }

    @Override
    public String addTask() {
        return "     Got it. I've added this task: \n" + "     [D][✗] " + task + " (by: " + time + ")";
    }

}
