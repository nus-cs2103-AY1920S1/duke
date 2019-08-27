import java.time.LocalDateTime;

public class Deadline extends Task {
    private String deadlineString;
    private LocalDateTime deadlineDate;

    public Deadline(String description, String deadline) {
        super(description);

        StringBuilder temp = new StringBuilder();
        String[] deadlineArr = deadline.split(" ");

        temp.append(deadlineArr[0]);
        temp.append(":");

        for (int i = 1; i < deadlineArr.length; i++) {
            temp.append(" ");
            temp.append(deadlineArr[i]);
        }

        this.deadlineString = temp.toString();
        this.deadlineDate = storeAsDateTime(deadlineString);
    }

    @Override
    public String toString() {
        String task = "[D][" + this.getStatusIcon() + "] " + description + " (" + deadlineString + ")";
        return task;
    }
}