public class TodoException extends TaskException {
    public TodoException(String message, boolean desc){
        super(message);
        this.desc = desc;
        this.time = true; //always true as todo does not need a specific time/date
        this.type = "todo";
    }

}
