public class DukeException extends Exception {

    protected DukeExceptionType type;

    public DukeException(String error, DukeExceptionType type){
        super(error);
        this.type = type;
    }

    public void printError(){
        String horizontalLine = "    ____________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(format(outputError(this.type)));
        System.out.println(horizontalLine);
    }

    public String format(String text){
        return "     " + text + " The Duke is sad :(";
    }

    public String outputError(DukeExceptionType type){
        switch(type) {
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
