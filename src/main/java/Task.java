public class Task {
    private int index;
    private String details;

    public Task(int i, String s) {
        index  = i;
        details = s;
    }

    @Override
    public String toString() {
        return String.format("%d. %s", index, details);
    }
}
