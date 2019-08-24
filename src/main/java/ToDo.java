public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public void markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + this);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
