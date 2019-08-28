public class deadlineTask extends Task {
    //Add variable for deadline tasks
    String deadline;

    public deadlineTask(String inputTask, boolean complete, String endTime) {
        super(inputTask,complete);
        type = "D";
        deadline = endTime;
    }

    public String getTime() {
        return deadline;
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
