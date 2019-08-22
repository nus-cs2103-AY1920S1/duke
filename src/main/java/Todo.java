public class Todo extends Task {
    Todo(String taskName){
        super(taskName);
    }

    public String getTaskDetails(){
        String doneSymbol;
        if (isDone()) {
            doneSymbol = "✓";
        } else {
            doneSymbol = "✗";
        }
        return "  [T]" + "[" + doneSymbol + "] " + getTaskName();
    }

}
