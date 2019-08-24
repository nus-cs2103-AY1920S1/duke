public class Deadline extends Task {
    private char shortForm = 'D';
    protected String submissionTime;
    public Deadline(String name, String submissionTime) {
        super(name);
        this.submissionTime = submissionTime;
    }

    public Deadline(String name, String submissionTime, boolean isDone) {
        super(name, isDone);
        this.submissionTime = submissionTime;
    }

    public String getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(String submissionTime) {
        this.submissionTime = submissionTime;
    }

    @Override
    public char getShortForm() {
        return shortForm;
    }

    @Override
    public String toString() {
        return "[" + getShortForm() + "]" + super.toString() + " (by: " + submissionTime + ")";
    }
}
