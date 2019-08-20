
class Task{

    private boolean status;
    private String message;


    public Task (Boolean status, String message){
        this.status = status;
        this.message = message;
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

        return doneString + " " + message;
    }
}