public class Task {
    protected String description;
    protected boolean isDone;
    private String IDENTIFIER ="UnIqUE_kEy_4324345";

    /**
     * Constructor
     *
     * @param description user input describing task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Set symbol for completion status
     *
     * @return tick or X symbols
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Set symbol for completion status for storage file
     *
     * @return O or X symbols
     */
    public String getFileStatusIcon() {
        return (isDone ? "O" : "X");
    }



    /**
     * Mark task as completed
     */
    public void setDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + this.description;
    }

    public String toFileString() {
        return "[" + getFileStatusIcon() + "]" + this.description;
    }


    private String format(String command, String cmdPrefix, String datePrefix ) {
        String unprocessed = command;
        String[] temp = unprocessed.split("/", 2);
        String dateString = temp[1];
        String formattedDateString = datePrefix + dateString + ")";

        return cmdPrefix + temp[0] + formattedDateString;
    }

    public String getString(String str, String cmd, String prefix, String datePrefix) {
        String task = str;
        if (this.isFromInput(task)) {
            String temp = this.cleanString(task);
            String result = temp.replaceAll(cmd, " ");
            return this.format(result, prefix, datePrefix);
        } else {
            return prefix + task;
        }
    }

    public boolean equals(Task t) {
        //System.out.println(t.toString());
        //System.out.println(this.toString());
        return t.toString().equals(this.toString());
    }

    public String cleanString(String str) {
        String result = str.replaceAll(IDENTIFIER,"");
        return result;
    }

    public boolean isFromInput(String str) {
        return str.contains(IDENTIFIER);
    }





}
