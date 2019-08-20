public class ToDos extends Task {

    public ToDos(String name) {
        super(name);
        this.type = "[T}";
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
}