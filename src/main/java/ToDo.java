class ToDo extends Task {

    ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        String iconForDone = done ? "\u2713" : "\u2718";
        return String.format("[T][%s] %s", iconForDone, this.task);
    }
}