class ToDoTask extends Task{

    public ToDoTask (Boolean status, String message){
        super(status, message, "[T]");
    }

    @Override
    public String toString() { 
        String doneString;
        if(super.status){
            doneString = "[✓]";
        }else{
            doneString = "[X]";
        }
        return super.type + doneString + " " + super.message;
    }

    public String toFileFormat(){
        String doneString;
        if(super.status){
            doneString = "[✓]";
        }else{
            doneString = "[X]";
        }
        return "T|" +doneString + super.message + "\n";
    }
}