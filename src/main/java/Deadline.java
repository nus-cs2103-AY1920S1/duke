public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toStringForHardDisk() {
        String[] datas = new String[4];
        datas[0] = "D";
        datas[1] = isDone ? "1" : "0";
        datas[2] = description;
        datas[3] = by;

        return String.join(" | ", datas);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
