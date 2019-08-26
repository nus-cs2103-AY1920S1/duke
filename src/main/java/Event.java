public class Event extends Task {

    public Event(String taskName, String at) {
        super(taskName);
        this.taskType = TypeOfTask.EVENT;

        if ( at.contains("(at: ") ) this.details = at;
        else this.details = "(at: " + at + ")";
    }

    public Event(String taskName, String at, boolean isCompleted) {
        super(taskName, isCompleted);
        this.taskType = TypeOfTask.EVENT;

        if ( at.contains("(at: ") ) this.details = at;
        else this.details = "(at: " + at + ")";
    }


}
