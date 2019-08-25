class ToDo extends Task {

    ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        String iconForDone = done ? "v" : "x";
        return String.format("[T][%s] %s", iconForDone, this.task);
    }
}