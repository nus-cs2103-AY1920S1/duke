public class TaskWithDate extends TaskWithPrefix {
    protected String date;

    public TaskWithDate(String line) {
        super(line);
    }

    /**
     * Rebuild variables based on params
     * @param data [0] contains description, [1] contains date. if Length is 1, means date is invalid
     */
    public void rebuild(String[] data) {
        this.description = data[0];
        if (data.length > 1) {
            this.date = data[1];
        } else {
            this.date = "";
        }
    }

    public TaskWithDate(String description, String date) {
        super(description);
        this.date = date;
    }

    protected String[] extractDataFromLine(String line, String delimiter) {
        return line.split(delimiter);
    }
}
