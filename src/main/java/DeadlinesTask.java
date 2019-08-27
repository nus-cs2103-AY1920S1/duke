class DeadlinesTask extends Task{
    
    // Note that the endingDateTime may have null values for
    // its time 
    DateTime endingDateTime;

    public DeadlinesTask (Boolean status, String message, DateTime endingDateTime){
        super(status, message, "[D]");
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
        return  super.type + doneString + " " + super.message + " (by: " +endingDateTime + ")";
    }

    public String toFileFormat(){
        String doneString;
        if(super.status){
            doneString = "1";
        }else{
            doneString = "0";
        }
        return "D|" +doneString + "|"+ super.message+ "|" + endingDateTime.toFileString() + "\n";
    }
}