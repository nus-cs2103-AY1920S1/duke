public class Task {
    private String taskName;
    private String taskIcon;
    private boolean isDone;

    public Task(String taskName, String taskIcon) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskIcon = taskIcon;
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

    private String getTaskIcon() {
        return this.taskIcon;
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + "[" + this.getDoneSymbol() + "] " + this.getName();
    }
}
