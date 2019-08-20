public class Deadlines extends Task {
    String submitBy;

    public Deadlines(String name, String  submitBy) {
        super(name);
        this.type = "[D]";
        this.submitBy = submitBy;
    }

    public String toString() {
        String output = "";
        if (this.done) {
            output = this.type + "[✓] " + this.name + "(by:" + this.submitBy + ")";
        } else {
            output = this.type + "[X] " + this.name + "(by:" + this.submitBy + ")";
        }
        return output;
    }
}

