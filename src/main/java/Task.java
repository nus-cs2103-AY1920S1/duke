public abstract class Task {
    private String name;
    private boolean done;

    public Task(String name){
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void done() {
        done = true;
    }

    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder();
        if (done) {
            toPrint.append("[✓] ");
        } else {
            toPrint.append("[✗] ");
        }
        toPrint.append(name);
        return toPrint.toString();
    }
}

