public class Task {
    private boolean done;
    private String name;
    private String type;

    public Task(String name) {
        this.done = false;
        this.name = name;
        this.type = type;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone() {
        this.done = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String gettype() {
        return name;
    }

    public void settype(String name) {
        this.name = name;
    }

    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        String s = "[" + getStatusIcon() + "] " + getName();
        return s;
    }

    public String toStringintxt() {
        String check = this.done==true ? "1," : "0,";
        String str = check + this.getName();
        return str;
    }
}
