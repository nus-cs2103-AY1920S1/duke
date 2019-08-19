public class Deadline extends Task {
    private String date;

    public Deadline(String desc, String date) {
        super(desc);
        this.date = date;
    }

    public String toString() {
        String d = String.format("[D][%s]%s(by:%s)",
                    this.getStatusIcon(), this.description, this.date);
        return d;
    }
}
