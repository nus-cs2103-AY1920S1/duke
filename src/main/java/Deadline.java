public class Deadline extends Task {
    protected String endDate;

    public Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
        this.typeOfTask = "D";
    }

    @Override
    public String toString() {
        String[] prepositionSplit = endDate.split(" ", 2);
        String statusIcon = this.getStatusIcon();
        return ("[" + typeOfTask + "]" + "[" + statusIcon + "] "
                + description + "(" + prepositionSplit[0] + ": "
                + prepositionSplit[1] + ")");
    }
}
