public class ToDos extends Task {

    public ToDos(String name) {
        super(name);
        this.type = "[T]";
    }

    @Override
    public String toString() {
        String output = "";
        if (this.done) {
            output = this.type + "[✓]" + this.name;
        } else {
            output = this.type + "[X]" + this.name;
        }
        return output;
    }

    @Override
    public String saveText() {
        String output = "T|";
        if(this.done) {
            output += "1|";
        } else {
            output += "0|";
        }
        output += this.name;
        return output;
    }
}