public class Task {
    private String task;

    Task(String string) {
        this.task = string;
    }

    @Override
    public String toString() {
        return this.task;
    }
}
