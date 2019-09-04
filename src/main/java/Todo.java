public class Todo extends Task {

    public Todo(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String toString() {
        if(isDone) {
            return "[T}[✓] " + name;
        } else {
            return "[T}[✗] " + name;
        }
    }
}
