public class Deadlines extends Task{

    protected String date;

    public Deadlines(String description, String date){
        super(description);
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }

    @Override
    public String toString(){
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + " (by: " + date + ")";
    }
}
