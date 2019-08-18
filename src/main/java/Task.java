class Task {
    boolean done;
    String task;

    Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void setDone(boolean status) {
        this.done = status;
    }

    @Override
    public String toString() {
        String iconForDone = done ? "v" : "x";
        return String.format("[%s] %s", iconForDone, this.task);
    }
}

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