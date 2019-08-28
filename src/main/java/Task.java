public class Task {
    private String taskName;
    private boolean isDone = false;
    public Task(String taskName){
        this.taskName = taskName;
    }

    /**
     * Make the task done
     */
    public void done() {
        isDone = true;
    }

    /**
     * Check if the task is done
     * @return boolean of if the task is done
     */
    public boolean isDone(){
        return isDone;
    }

    /**
     * Get the name of the task
     * @return yhe string representation of the task
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Get the full task details
     * @return the string of the task details
     */
    public String getTaskDetails() {
        String doneSymbol;
        if (isDone) {
            doneSymbol = "✓";
        } else {
            doneSymbol = "✗";
        }
        return "[" + doneSymbol + "] " + taskName;
    }
}
