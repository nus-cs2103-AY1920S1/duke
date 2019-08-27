public class deadlineTask extends Task {
    String deadline;

    public deadlineTask(String inputTask, String endTime) {
        super(inputTask);
        type = "D";
        deadline = endTime;
    }

    @Override
    public String toString() {
        if(completed) {
            return "[" + type  + "]" + "[\u2713] " + name + "(by: " + deadline + ")";
        } else {
            return "[" + type + "]" + "[\u2718] " + name + "(by: " + deadline + ")";
        }
    }


}
