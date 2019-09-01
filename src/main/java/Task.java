public abstract class Task {
    protected String description;
    protected Boolean isdone;

    protected Task (String description) {
        this.description = description;
        this.isdone = false;
    }

    protected String getDescription() {
        return this.description;
    }

    protected String getStatusIcon() {
        return (isdone ? "\u2713" : "\u2718");
    }

    protected String checkStatus() {
        if (isdone == true) {
            return "1";
        } else {
            return "0";
        }
    }

    protected void recoverStatus(String status) {
        if (status.equals("1")) {
            isdone = true ;
        } else {
            isdone = false;
        }
    }

    protected void markAsDone() {
        isdone = true;
    }

    public abstract String formatString();

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

}
