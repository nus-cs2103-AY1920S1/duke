public abstract class Task {
    private static int NOT_DONE = 0;
    private static int DONE = 1;

    private String name;
    private int status;

    Task(String name) {
        this.name = name;
        this.status = NOT_DONE;
    }

    protected abstract String getTypeSymbol();
    protected abstract String getAdditionalInfo();

    protected void setDone() {
        this.status = DONE;
    }

    protected void setNotDone() {
        this.status = NOT_DONE;
    }

    private int getStatus() {
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
        return String.format("%s%s %s %s", getTypeSymbol(), getStatusSymbol(), name, getAdditionalInfo());
    }
}
