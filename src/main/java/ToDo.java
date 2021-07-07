import java.text.ParseException;

public class ToDo extends Task {
    // task with nothing attached, so no frills, everything can be simply inherited
    public ToDo(String info, String type, String by) throws ParseException {
        /**
         *  overrides original constructor
         *  @params String info: task information
         * @params String type: type of task
         * @return none
         */
        super(info,"T","");
    }
}
