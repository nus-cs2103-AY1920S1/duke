public class Task {
    String description;
    Boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public void doTask() {
        this.status = true;
    }

    public String getStatusIcon() {
        return status ? "[\u2713]" : "[\u2718]";
    }

    @Override
    public String toString() {
       return String.format("%s %s", this.getStatusIcon(), this.description);
    }
}
