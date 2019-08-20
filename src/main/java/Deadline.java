public class Deadline extends Task{
    String date_Time;
    public Deadline(String description){
        super(description);
        int divider = description.indexOf("/by");
        date_Time = description.substring(divider + 4, description.length());
        super.description = super.description.substring(0, divider);
    }

    @Override
    public String toString(){
        String output = "[D][" + super.getStatusIcon() + "]" + " " + super.description + "(by: " + date_Time + ")";
        return output;
    }
}
