public class Todo extends Task {
    Todo(String taskName){
        super(taskName);
    }

    public String getTaskDetails(){
        String doneSymbol;
        if (isDone()) {
            doneSymbol = "Yes";
        } else {
            doneSymbol = "No";
        }
        return "  [T]" + "[" + doneSymbol + "] " + getTaskName();
    }

}
