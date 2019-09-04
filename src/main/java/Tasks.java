public class Tasks {
    private int status;
    private String details;

    public Tasks(String details) {
        this.status = 1;
        this.details = details;
    }

    public void finishTask() {
        this.status = 0;
    }

    public String getDetails() {
        return this.details;
    }

    public int getStatus() {
        return this.status;
    }

}