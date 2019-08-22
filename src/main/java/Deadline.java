public class Deadline extends Task {
    private String deadline;

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

        this.deadline = temp.toString();
    }

    @Override
    public String toString() {
        String task = "[D][" + this.getStatusIcon() + "] " + description + " (" + deadline + ")";
        return task;
    }
}