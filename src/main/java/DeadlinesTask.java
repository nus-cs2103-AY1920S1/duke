class DeadlinesTask extends Task{

    String deadline;

    public DeadlinesTask (Boolean status, String message, String deadline){
        super(status, message, "[D]");
        this.deadline = deadline;
    }

    @Override
    public String toString() { 
        String doneString;
        if(super.status){
            doneString = "[✓]";
        }else{
            doneString = "[X]";
        }
        return super.type + doneString + " " + super.message + deadline;
    }
}