public abstract class Task {
    protected String description;
    protected Boolean isdone;

    public Task (String description) {
        this.description = description;
        this.isdone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isdone ? "\u2713" : "\u2718");
    }

    public String checkStatus() {
        if (isdone == true) {
            return "1";
        } else {
            return "0";
        }
    }

    public void recoverStatus(String status) {
        if (status.equals("1")) {
            isdone = true ;
        } else {
            isdone = false;
        }
    }

    public void markasdone() {
        isdone = true;
    }

    public abstract String formatString();

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

}
