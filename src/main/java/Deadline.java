public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, int doner){
        super(description);
        if(doner == 1){
            super.completed();
        }
        this.by = by;
    }

    @Override
    public String save(){
        int a = 0;
        if(super.isDone){
            a = 1;
        }
        return "D|" + a + "|" + super.description + "|" + by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}