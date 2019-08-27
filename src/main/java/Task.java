public abstract class Task {
    protected boolean isComplete;
    protected String description;

    public Task(String description) {
        this.isComplete = false;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
    
    public String getStatusIcon() {
        // Does not return the UTF-16 character for a tick or cross respectively
        // Java SDK 11 incorrectly encodes Unicode characters on Windows returning ? for all of them
        return this.isComplete ? "V" : "X";
    }

    public void complete() {
        this.isComplete = true;
    }

    public abstract String toEncodedString();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
