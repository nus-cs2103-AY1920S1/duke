public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
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

