import cs2103t.duke.date.MyDate;
import cs2103t.duke.exceptions.DukeIllegalArgumentException;

public class Event extends Task {

    protected MyDate at;
    
    public Event(String description, String at) {
        super(description);
        this.at = MyDate.genMyDate(at);
    }

    public static Event genEvent(String s) {
        String[] newInputArray = s.split("/at");
        String des, at;
        try {
            des = newInputArray[0].trim();
            at = newInputArray[1].trim();
            return new Event(des, at);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Illegal input for event. "
                    + "Please key in 'event <task> /at <time>'");
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentException("Illegal input for time. Please key in the format "
                    + "'DD/MM/YYYY'[SPACE]'hhmm-hhmm'");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: " + at.toString();
    }
}
