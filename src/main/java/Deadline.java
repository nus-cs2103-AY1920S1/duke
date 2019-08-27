public class Deadline extends Task {
    private String date;

    /**
     * Creates a Deadline object, which is also a Task.
     * @param desc a description of the Deadline Task.
     * @param date the date of the Deadline which is to be done by.
     */
    public Deadline(String desc, String date) {
        super(desc);
        this.date = date;
    }

    /**
     * Returns a string representation of a Deadline object.
     * @return String Returns a string representation of a Deadline object.
     */
    public String toString() {
        String d = String.format("[D][%s]%s(by:%s)",
                    this.getStatusIcon(), this.description, this.date);
        return d;
    }
}
