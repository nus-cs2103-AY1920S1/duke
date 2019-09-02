public class Deadlines extends Task {

    protected String date;

    public Deadlines(String name, boolean completionStatus , String by) {
        super(name,completionStatus);
        this.date = by;
    }
    @Override
    public String getOverallStatus() {
        return "[D]" + getCurrentStatus() + Description + "(by:" + date + ")";
    }

    @Override
    public String encodeForStorage() {
        int myInt = isDone ? 1 : 0;
        return "deadline [" + myInt + "]" + Description + "/by" + date;
    }

}
