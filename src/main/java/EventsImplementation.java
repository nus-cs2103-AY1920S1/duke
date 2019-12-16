import java.util.Date;
import java.text.SimpleDateFormat;

class EventsImplementation implements TaskInterface,  
    TypedTaskInterface {
    private final String name;
    private final String date;
    private final Date objDate;
    private final boolean isDone;
    private final TaskFormatInterface formatter;
    private final String sym = "E";

    public EventsImplementation(String name, String date, 
            boolean isDone) {
        this.name = name;
        this.date = date;
        this.objDate = setDate(date);
        this.isDone = isDone;
        String outputText = String.format("%s (at: %s)", 
                name, date);
        this.formatter = new TypedTaskFormatter(this);
    }

    private Date setDate(String date) {
        try {
            Date toSet = new SimpleDateFormat("dd/MM/yyy HHmm")
                .parse(date);
            return toSet;
        } catch (Exception e) {
            return null;
        }
    }

    public TaskInterface completeTask() {
        return 
            new EventsImplementation(this.name, this.date, true);
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getSymbol() {
        return this.sym;
    }

    public String getPrintDate() {
        if (this.date.length() > 0) {
            return "at: " + getDate();
        } else {
            return "";
        }
    }

    public String getDate() {
        if (this.date.length() > 0) {
            return this.date;
        } else {
            return "";
        }
    }

    // Override
    @Override
    public String toString() {
        return this.formatter.formatText();
    }

    public String getSaveFormat() {
        return this.formatter.getSaveFormat();
    }
          
}
