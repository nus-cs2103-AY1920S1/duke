/** Task to represent a To-do. */
class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String doneStr = this.done ? "✓" : "✗";
        return String.format("[T][%s] %s", doneStr, this.name);
    }
}