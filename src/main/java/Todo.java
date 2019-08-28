public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String printForStorage() {
        String borderAndSpace = " | ";
        String str = super.printForStorage();
        str += "T" + borderAndSpace;
        if (this.isDone) {
            str += "1" + borderAndSpace;
        } else {
            str += "0" + borderAndSpace;
        }
        str += this.name + borderAndSpace;
        return str;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}