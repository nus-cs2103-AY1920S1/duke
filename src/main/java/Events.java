public class Events extends Task{

    protected String date;

    public Events(String description, String date){
        super(description);
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }

    @Override
    public String toString(){
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + " (at: " + date + ")";
    }
}
