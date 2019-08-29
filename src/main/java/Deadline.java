public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String toString() {
        String statusIcon = getStatusIcon();
        return "[D][" + statusIcon + "] " + this.description + " (by: " + this.deadline + ")";
    }

    public String getStorageFormat() {
        String storageString = super.getStorageFormat() + " | " + this.deadline;
        return storageString;
    }
}
