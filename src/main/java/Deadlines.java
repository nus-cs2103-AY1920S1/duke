public class Deadlines extends Task {
    private String name;
    private String datetime;

    Deadlines(String name, String datetime) {
        super(name);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[D]" +  super.toString() + " (by: " + datetime + ")";
    }
}
