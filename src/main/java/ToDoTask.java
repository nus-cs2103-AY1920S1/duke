public class ToDoTask extends Task {

    public ToDoTask(String todo) {
        super(todo);
    }

    public ToDoTask(String todo, boolean isCompleted) {
        super(todo, isCompleted);
    }

    public String toString() {
        if (isCompleted) {
            return  String.format("[T][Y] %s", this.todo);
        } else {
            return  String.format("[T][N] %s", this.todo);
        }
    }

}
