public class Deadline extends Task{
    DateTime date_Time;
    public Deadline(String description) throws DukeException{
        super(description);
        int divider = description.indexOf("/by");
        if (divider == -1 || (divider == description.length() - 3)){
            throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Incorrect Deadline format.\n " +
                    "    Please key in Deadline (taskname) /by date(d/MM/yyyy) time(HHmm) \n" + (new Border()));
        }
        date_Time = DateTime.setDeadline(description.substring(divider + 4, description.length()));
        super.description = super.description.substring(0, divider);
    }
    public Deadline(String description, DateTime date_Time){
        super(description);
        this.date_Time = date_Time;
    }

    @Override
    public String toString(){
        String output = "[D][" + super.getStatusIcon() + "]" + " " + super.description
                + "(by: " + date_Time.toString() + ")";
        return output;
    }
}
