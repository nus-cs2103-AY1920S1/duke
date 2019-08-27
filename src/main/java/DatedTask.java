public abstract class DatedTask extends Task {
    private String dateTime;

    DatedTask(String icon, String description, String dateTime) {
        super(icon, description);
        this.dateTime = dateTime;
    }

    String
    String getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat().concat(String.format("|%s", this.getDateTime()));
    }
}
