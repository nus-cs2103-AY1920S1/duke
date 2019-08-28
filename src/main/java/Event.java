public class Event extends Task{
    DateTime date_Time;
    public Event(String description) throws DukeException{
        super(description);
        int divider = description.indexOf("/at");
        if (divider == -1 || (divider == description.length() - 3)){
            throw new DukeException((new Border()) + "\n     ☹ OOPS!!! The date/time cannot be empty.\n" + (new Border()));
        }
        date_Time = DateTime.setEventTime(description.substring(divider + 4, description.length()));
        super.description = super.description.substring(0, divider);
    }

    @Override
    public String toString(){
        String output = "[E][" + super.getStatusIcon() + "]" + " " + super.description + "(at: "
                + date_Time.toString() + ")";
        return output;
    }
}
