public class Todo extends Task {

    Todo(String task_name) {
        super(task_name);
    }

    @Override
    String task_info() {
        String indicator;
        if (isFinished()) indicator = "[\u2713] ";
        else indicator = "[\u2715] ";
        return "[T]" + indicator + get_name();
    }

    @Override
    String record_info() {
        if (isFinished()) return "T|" + "1|" + get_name();
        else return "T|" + "0|" + get_name();
    }
}
