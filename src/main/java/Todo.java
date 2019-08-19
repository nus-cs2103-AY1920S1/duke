public class Todo extends Task {

    public Todo(String task_name) {
        super(task_name);
    }

    @Override
    public String task_info() {
        String indicator;
        if (isFinished()) indicator = "[\u2713] ";
        else indicator = "[\u2715] ";
        return "[T]" + indicator + get_name();
    }
}
