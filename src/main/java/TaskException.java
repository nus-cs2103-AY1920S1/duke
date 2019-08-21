public class TaskException extends DukeException {
    protected boolean desc;
    protected boolean time;
    protected String type;

    public TaskException(String message) {
        super(message);
        desc = false;
        time = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(!desc && !time){
            sb.append("description & specific time/date ");
        } else {
            if (!desc) {
                sb.append("description ");
            }
            if (!time) {
                sb.append("specific time/date ");
            }
        }
        return "☹ OOPS!!! The " + sb.toString() + "of a " + type + " cannot be empty.";
    }
}
