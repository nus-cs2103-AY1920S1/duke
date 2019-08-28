import java.text.ParseException;

public class Event extends Task {
    public Event(String info, String type, String by) {
        /**
         *  overrides original constructor
         *  @params String info: task information
         * @params String type: type of task
         * @params String by: when event actually is
         * @return none
         */
        super(info,type,by);
    }
    @Override
    public String printTask() {
        /**
         *  overrides original print task from superclass
         * @return formatted string containing info and status of task
         *  including when event is
         */
        return super.printTask() + " (at: " + by + ")";
    }

}
