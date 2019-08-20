public class Task {
    private String taskName;
    private boolean isDone = false;
    public Task(String taskName){
        this.taskName = taskName;
    }

    public void done() {
        isDone = true;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDetails(){
        String doneSymbol;
        if (isDone) {
            doneSymbol = "Yes";
        } else {
            doneSymbol = "No";
        }
        return "[" + doneSymbol + "] " + taskName;
    }
}
