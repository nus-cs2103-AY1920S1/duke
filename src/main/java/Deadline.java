public class Deadline extends Task {
    protected String submissionTime;
    public Deadline(String name, String submissionTime) {
        super(name);
        this.submissionTime = submissionTime;
    }

    public String getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(String submissionTime) {
        this.submissionTime = submissionTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + submissionTime + ")";
    }
}
