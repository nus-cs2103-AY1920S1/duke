public class Event extends Task {
    String eventDate;
    Event(String taskName, String eventDate){
        super(taskName);
        this.eventDate = eventDate;
    }

    public String getTaskDetails(){
        String doneSymbol;
        if (isDone()) {
            doneSymbol = "Yes";
        } else {
            doneSymbol = "No";
        }
        return "  [E]" + "[" + doneSymbol + "] " + getTaskName() + " (at: " + eventDate + ")";
    }
}
