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

class Deadline extends Task {
    String date;

    Deadline(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        String iconForDone = done ? "v" : "x";
        return String.format("[D][%s] %s (by: %s)", iconForDone, this.task, this.date);
    }
}

class Event extends Task {
    String date;

    Event(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        String iconForDone = done ? "v" : "x";
        return String.format("[E][%s] %s (at: %s)", iconForDone, this.task, this.date);
    }
}