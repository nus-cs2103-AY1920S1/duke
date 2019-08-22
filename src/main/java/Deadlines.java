public class Deadlines extends Task {
    private String datetime;

    Deadlines(String name, String datetime, boolean done) {
        super(name, done);
        this.datetime = datetime;
    }

    Deadlines(String name, String datetime) {
        this(name, datetime, false);
    }

    @Override
    public String storageString() {
        return "D," +
                (super.getDone() ? "1," : "0,") +
                super.getName() +
                "," +
                this.datetime;
    }

    @Override
    public String toString() {
        return "[D]" +  super.toString() + " (by: " + datetime + ")";
    }
}
