public class DukeException extends Exception {

    protected DukeExceptionType type;

    public DukeException(DukeExceptionType type){
        this.type = type;
        String error = outputErrorMessage();
        super(error);
    }

    public String outputErrorMessage(){
        switch(this.type) {
        case INVALIDCOMMAND:
            return "Invalid Command! Please try again.";
        case MISSINGDATE:
            return "The event/deadline is missing a date!";
        case MISSINGTASK:
            return "Please specify the task!";
        case TASKNOTFOUND:
            return "Task is not in your list";
        case TASKALREADYDONE:
            return "Task has already been done!";
        case MISSINGDESCRIPTION:
            return "Task has no description, please enter again.";
        default:
            return "Something has gone wrong! Please try again.";
        }
    }
}
