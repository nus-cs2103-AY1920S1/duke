public class Tasks {
    private int status;
    private String details;
    private String type;

    public Tasks(String details, String type) {
        this.status = 1;
        this.details = details;
        this.type = type;
    }

    public Tasks(String details, int status) {
        this.status = status;
        this.details = details;
    }

    public void doTask() {
        this.status = 0;
    }

    public String getDetails() {
        return this.details;
    }

    public int getStatus() {
        return this.status;
    }

    public String getType() {
        return this.type;
    }

}