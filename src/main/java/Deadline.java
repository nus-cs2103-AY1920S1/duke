import java.util.GregorianCalendar;

public class Deadline extends Task{
    private boolean isDate ;  //is String or calendar-date
    private String by;
    private GregorianCalendar date = new GregorianCalendar();
    public Deadline() {
        //default constructor
    }

    public Deadline(String taskName, int index, String by) {
        super(taskName, index);
        this.by = by;
        this.date = null;
        this.isDate = false;
        super.setType('D');
    }
    public Deadline (String taskName, int index, String by, GregorianCalendar date) {
        super(taskName, index);
        this.isDate = true;
        this.date = date;
        this.by = by;
        super.setType('D');
    }
    public GregorianCalendar getDate () {
        return date;
    }
    public boolean getIsDate (){ return  isDate; }
    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
