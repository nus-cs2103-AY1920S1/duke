public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks Task as Done
     */
    public void markDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this);
    }

    /**
     * Returns taskName
     * @return String name
     */
    public String getName() {
        return this.taskName;
    }

    /**
     * Returns tick if task is Done, else returns cross
     * @return String symbol
     */
    private String getDoneSymbol () {
        if(this.isDone) {
            return "✓";
        }
        return "✗";
    }

    @Override
    public String toString() {
        return "[" + this.getDoneSymbol() + "] " + this.getName();
    }
}
