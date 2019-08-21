public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String str = "["
                + "T"
                + "]["
                + this.getStatusIcon()
                + "] "
                + this.getDescription();
        return str;
    }
}