public class Deadline extends Task {
    String endDate;
    Deadline(String taskName, String endDate){
        super(taskName);
        this.endDate = endDate;
    }

    public String getTaskDetails(){
        String doneSymbol;
        if (isDone()) {
            doneSymbol = "✓";
        } else {
            doneSymbol = "✗";
        }
        return "  [D]" + "[" + doneSymbol + "] " + getTaskName() + " (by: " + endDate + ")";
    }
}
