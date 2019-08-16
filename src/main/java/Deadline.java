public class Deadline extends TaskWithDate {

    public Deadline(String line) {
        super(line);
        rebuild(extractDataFromLine(description, " /by "));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}