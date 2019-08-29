abstract class Task {

    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    String getDescription() {
        return this.description;
    }

    boolean isDone() {
        return this.isDone;
    }

    abstract String getType();

    String getStatus() {
        String icon = (isDone ? "\u2713" : "\u2718"); //tick or X symbol
        return String.format("[%s] %s", icon, this.description);
    }

    void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.description;
    }

}