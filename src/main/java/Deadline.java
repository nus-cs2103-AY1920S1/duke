public class Deadline extends Task {

    private String deadlineDate;
    private String deadlineTime;

    public Deadline (String deadlineName, String deadlineDate, String deadlineTime) {
        super(deadlineName);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String getTaskName() {
        return super.getTaskName() + "( " + deadlineDate + " " + deadlineTime + " )";
    }

    @Override
    public char getRepLetter() {
        return 'D';
    }
}
