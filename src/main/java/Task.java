public class Task {
    private String name;
    private boolean isDone;
    private Inputs Type;

    enum Inputs {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String name, String input) {
        this.name = name;
        this.Type = Inputs.valueOf(input);
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        if(isDone) {
            return "[✓] " + name;
        } else {
            return "[✗] " + name;
        }
    }
}
