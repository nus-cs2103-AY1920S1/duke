public class Todo extends Task {

    String time;

    public Todo(int num, String task, String type) {
        super(num, task, type);
    }

    public String toString() {
        return task;
    }

    @Override
    public String addTask() {
        return "     Got it. I've added this task: \n       [T][✗] ";
    }
}
