public class Task {

    private String name;
    private boolean doneStatus;

    public Task(String name) {
        this.name = name;
        doneStatus = false;
    }

    public void taskComplete() {
        doneStatus = true;
    }

    @Override
    public String toString() {
        String output = "";
        if (doneStatus == true) {
            output += "[✓]";
        } else {
            output += "[✗]";
        }
        output += name;
        return output;
    }
}