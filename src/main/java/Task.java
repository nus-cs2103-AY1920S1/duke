public class Task {

    // all class variables private

    private String taskInfo;
    private boolean isDone;
    private String taskType;

    public Task(String info, String type) {
        /**
         *  constructor, saves info of task
         *  @params String info: task information
         * @params String type: type of task
         * @return formatted string containing info and status of task
         */
        this.taskInfo = info;
        this.isDone = false;
        this.taskType = type;
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
