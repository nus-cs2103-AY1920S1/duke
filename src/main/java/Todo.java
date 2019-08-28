public class Todo extends Task {
    Todo(String taskName){
        super(taskName);
    }

    /**
     * the details for the todo task
     * @return string
     */
    public String getTaskDetails(){
        String doneSymbol;
        if (isDone()) {
            doneSymbol = "✓";
        } else {
            doneSymbol = "✗";
        }
        return "[T]" + "[" + doneSymbol + "] " + getTaskName();
    }

}
