public class Task {

    // all class variables private

    protected String taskInfo;
    protected boolean isDone;
    protected String taskType;
    protected String by;

    public Task(String info, String type,String by) {
        /**
         *  constructor, saves info of task
         *  @params String info: task information
         * @params String type: type of task
         * @return formatted string containing info and status of task
         */
        this.taskInfo = info;
        this.isDone = false;
        this.taskType = type;
        this.by = by;
    }
    // getters
    public String getTaskInfo() {
        /**
         *  getter of info of task
         * @return String task info
         */
        return taskInfo;
    }
    public String getStatus() {
        /**
         *  getter of status of task
         * @return tick or cross depending on isDone
         */
        // taken from partial soln tq prof
        return (isDone ? "\u2713" : "\u2718");
    }
    public boolean getDone() {
        return isDone;
    }
    public String getType() {
        /**
         *  getter of task type
         * @return tick or cross depending on isDone
         */
        return taskType;
    }
    public String printTask() {
        /**
         *  prints task in formatted string
         * @return formatted string
         */
        String s = "[" + getType() + "]";
        s += "[" + getStatus() + "] ";
        s += getTaskInfo();
        return s;
    }
    public String getBy() {
        return by;
    }
    // setters
    public void markDone() {
        /**
         *  setter to mark task is done
         *   changes output of getStatus
         * @params none
         * @returns none
         */
        this.isDone = true;
    }


}
