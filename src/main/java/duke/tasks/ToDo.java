package duke.tasks;

public class ToDo extends Task {

    public ToDo(String description){
        super(description);
    }

    public ToDo(String description, int doner){
        super(description);
        if(doner == 1){
            super.completed();
        }
    }

    @Override
    public String save(){
        int a = 0;
        if(super.isDone){
            a = 1;
        }
        return "T|" + a + "|" + super.description ;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}