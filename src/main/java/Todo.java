
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean completed) {
        super(name, completed);
    }

    @Override
    public String toString() {
        String result = "[T][";
        result = this.completed ? result + "\u2713" + "]" : result + "\u2718" + "]";
        result += " " + this.name;
        return result;
    }
}
