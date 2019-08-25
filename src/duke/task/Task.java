package duke.task;

public abstract class Task {
    public static int NOT_DONE = 0;
    public static int DONE = 1;

    private String name;
    private int status;

    Task(String name) {
        this.name = name;
        this.status = NOT_DONE;
    }

    protected abstract String getTypeSymbol();
    public abstract String getAdditionalInfo();
    protected abstract String displayAdditionalInfo();

    protected void setDone() {
        this.status = DONE;
    }

    protected void setNotDone() {
        this.status = NOT_DONE;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    private String getStatusSymbol() {
        if (status == DONE) {
            return "[✓]";
        } else {
            return "[✗]";
        }
    }

    @Override
    public String toString() {
        return String.format("%s%s %s %s", getTypeSymbol(), getStatusSymbol(), name, displayAdditionalInfo());
    }
}
