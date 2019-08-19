public class Todo extends Task {

    public Todo(String task){
        super(task);
    }
    public String toString(){
        String box;
        String msg = "[T]";
        if (!this.isDone()){
            box = "[✗] ";
        } else{
            box = "[✓] ";
        }
        return msg + box + this.getTask() ;
    }
    public void printAddedTodo (int size){
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        String end = " tasks in the list.";
        if (size==1){
            end = " task in the list.";
        }
        System.out.println("Now you have " + size + end);
    }

}


