public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[T]");
        if(isDone) {
            sb.append("[✓] ");
        } else {
            sb.append("[✗] ");
        }
        sb.append(description);
        return sb.toString();
    }
}
