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

    @Override
    public String saveText() {
        String output = "E|";
        if(this.done) {
            output += "1|";
        } else {
            output += "0|";
        }
        output += this.name + "|" + this.time;
        return output;
    }
}
