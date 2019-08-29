import java.text.SimpleDateFormat;

class Task {
    String content;
    boolean done;
    static final int tick = '\u2713';
    static final int cross = '\u2717';
    static final SimpleDateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    static final SimpleDateFormat outputFormatter = new SimpleDateFormat("E, dd MMM yyyy ha");

    public Task(String content) {
        this.content = content;
    }

    public void complete() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done: \n  " + this.toString());
    }

    public String getTime() {
        return "";
    }

    public String toString() {
        return this.content;
    }
}