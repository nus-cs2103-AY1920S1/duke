public class Deadline extends Task {
    private String by;
    public Deadline(String info, String type, String by) {
        super(info,type);
        this.by = by;
    }
    @Override
    public String printTask() {
        return super.printTask() + " (by: " + by + ")";
    }

}
