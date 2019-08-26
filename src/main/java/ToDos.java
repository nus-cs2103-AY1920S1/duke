public class ToDos extends Task {

    public ToDos (String task) {
        super(task);
    }

    public ToDos (String task, boolean complete) {
        super(task, complete);
    }

    @Override
    public String toStringForFile() {
        String isComplete = this.complete ? "1" : "0";
        return "T | " + isComplete + " | " + task; 
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}