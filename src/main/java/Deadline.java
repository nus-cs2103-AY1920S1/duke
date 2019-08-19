public class Deadline extends Task{
    private String by;

    public Deadline() {
        //default constructor
    }

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
        setType('D');
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
