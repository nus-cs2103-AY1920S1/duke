public class ToDo extends Task {
    ToDo(String name, boolean done) {
        super(name, done);
    }

    ToDo(String name) {
        this(name, false);
    }

    @Override
    public String storageString() {
        return "T," +
                (super.getDone() ? "1," : "0,") +
                super.getName();
    }

    @Override
    public String toString() {
        return "[T]" +  super.toString();
    }
}
