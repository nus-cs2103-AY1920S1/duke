import java.util.GregorianCalendar;

public class Event extends Task {
    private boolean isDate ;  //is String or calendar-date
    private String start;
    private GregorianCalendar date = new GregorianCalendar();

    //date is string
    public Event(String taskName, int index, String start) {
        super(taskName, index);
        this.start = start;
        this.isDate = false;
        setType('E');
    }

    //date is calendar-date
    public Event(String taskName, int index, String start, GregorianCalendar date) {
        super(taskName, index);
        this.isDate = true;
        this.start = start;
        this.date = date;
        super.setType('E');
    }
    public GregorianCalendar getDate () {
        return date;
    }
    public String getStart() {
        return start;
    }

    public boolean getIsDate (){ return  isDate; }

    public void setStart(String start) {
        this.start = start;
    }
}
