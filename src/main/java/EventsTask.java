class EventsTask extends Task{

    DateTime startingDateTime ; 
    DateTime endingDateTime;

    public EventsTask (Boolean status, String message, 
    DateTime startingDateTime, DateTime endingDateTime){
        super(status, message, "[E]");

        this.startingDateTime = startingDateTime;
        this.endingDateTime = endingDateTime;
    }

    @Override
    public String toString() { 
        String doneString;
        if(super.status){
            doneString = "[✓]";
        }else{
            doneString = "[X]";
        }
        return super.type + doneString + " " + super.message +" (at: " + startingDateTime  + " to " +endingDateTime + ")";
    }

    public String toFileFormat(){
        String doneString;
        if(super.status){
            doneString = "[✓]";
        }else{
            doneString = "[X]";
        }
        return "T|" +doneString + super.message+ "/at " + startingDateTime.toFileString() +" "+ endingDateTime.toFileString() + "\n";
    }
}