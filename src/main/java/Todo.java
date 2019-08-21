public class Todo extends Task {

    String time;

    public Todo(int num, String task) {
        super(num, task);
    }

    @Override
    public String addTask() {
        return "     Got it. I've added this task: \n" + "     [T][✗] " + this.task;
    }
}
