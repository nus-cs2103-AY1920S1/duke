public class Task {

    private String description;
    private boolean doneStatus;

    public Task(String description) {
        this.description = description;
        doneStatus = false;
    }

    public void taskComplete() {
        doneStatus = true;
    }

    @Override
    public String toString() {
        String output;
        if (doneStatus == true) {
            output = "[✓]";
        } else {
            output = "[✗]";
        }
        output += " " + description;
        return output;
    }
}