import java.text.ParseException;

public class Deadline extends Task {

    public Deadline(String info, String type, String by) {
        /**
         *  overrides original constructor
         *  @params String info: task information
         * @params String type: type of task
         * @params String deadline do by date
         * @return none
         */
        super(info,type,by);
    }
    @Override
    public String printTask() {
        /**
         *  overrides original print task from superclass
         *  @params String info: task information
         * @params String type: type of task
         * @return formatted string containing info and status of task
         */
        return super.printTask() + " (by: " + by + ")";
    }

}
