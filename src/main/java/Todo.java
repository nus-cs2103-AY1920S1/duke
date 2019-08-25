public class Todo extends Task {

    public Todo(String name) {
        super(name, false);
    }

    public Todo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public Todo isDone() {
        return new Todo(super.name, true);
    }

    @Override
    public String toString() {
        String s = "";
        if(done) {
            s = s + "[T][✓]";
        } else {
            s = s + "[T][✗]";
        }

        return s + " " + name;
    }

    public String toIndicationInsideFile() {
        String s = "T | ";

        if(done) {
            s = s + "1 | ";
        } else {
            s = s + "0 | ";
        }

        return s + name;
    }
}
