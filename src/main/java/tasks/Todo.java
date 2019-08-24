package tasks;

import tasks.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String writer() {
        String text = "T | ";
        if(getStatus() == false){
            text = text.concat("0 | "+getDescription());
        }else{
            text = text.concat("1 | "+getDescription());
        }
        return text;
    }
}
