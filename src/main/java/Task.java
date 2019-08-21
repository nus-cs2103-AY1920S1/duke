
class Task{

    protected boolean status;
    protected String message;
    protected String type;


    public Task (Boolean status, String message, String type){
        this.status = status;
        this.message = message;
        this.type = type;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    @Override
    public String toString() { 
        String doneString;
        if(status){
            doneString = "[✓]";
        }else{
            doneString = "[X]";
        }
        return type + doneString + " " + message;
    }
}