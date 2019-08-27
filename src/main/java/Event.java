public class Event extends Task{

    protected String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    public Event(String description, String at, int doner){
        super(description);
        if(doner == 1){
            super.completed();
        }
        this.at = at;
    }

    @Override
    public String save(){
        int a = 0;
        if(super.isDone){
            a = 1;
        }
        return "E|" + a + "|" +  super.description + "|" + at;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}