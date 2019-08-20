public class Events extends Task {
    String time;

    public Events(String name, String time) {
        super(name);
        this.time = time;
        this.type = "[E]";
    }

    public String toString() {
        String output = "";
        if (this.done) {
            output = this.type + "[✓] " + this.name + "(at:" + this.time + ")";
        } else {
            output = this.type + "[X] " + this.name + "(at:" + this.time + ")";
        }
        return output;
    }
}
