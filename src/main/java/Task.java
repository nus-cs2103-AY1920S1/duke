public abstract class Task {
    String name;
    String type;
    Boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }
}
