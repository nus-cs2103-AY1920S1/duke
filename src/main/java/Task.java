public class Task {

    private String name;
    private String status;

    Task(String name) {
        this.name = name;
        this.status = "✗";
    }

    @Override
    public String toString() {  return "[" + this.status + "] " + this.name; }

    void finishTask() {this.status = "✓";}

}