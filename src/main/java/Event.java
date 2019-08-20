public class Event extends Task{
    String date_Time;
    public Event(String description){
        super(description);
        int divider = description.indexOf("/at");
        date_Time = description.substring(divider + 4, description.length());
        super.description = super.description.substring(0, divider);
    }

    @Override
    public String toString(){
        String output = "[D][" + super.getStatusIcon() + "]" + " " + super.description + "(at: " + date_Time + ")";
        return output;
    }
}
