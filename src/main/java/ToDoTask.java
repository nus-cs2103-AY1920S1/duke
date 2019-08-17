public class ToDoTask extends Task {

    public ToDoTask(String todo) {
        super(todo);
    }

    public String toString() {
        if (completed) {
            return  String.format("[T}[✓] %s", this.todo);
        } else {
            return  String.format("[T}[✗] %s", this.todo);
        }
    }

}
