import java.util.*;
public class Duke {
    private ArrayList<Task> todolist;
    public Duke(){
        this.todolist=new ArrayList<Task>();
    }
    private static void print(String message){
        System.out.println(">>"+message);
    }
    private void list(){
        StringBuilder outputmsg=new StringBuilder("List:");
        int i=todolist.size();
        for(int x=1; x<=i;x++) {
            outputmsg.append("\n  "+ x + "." + todolist.get(x - 1));
        }
        String output=outputmsg.toString();
        print(output);
    }
    private void run(String input) {
        int spacepos = input.indexOf(" ");
        if (spacepos == -1) {  //will change in level-5
            System.out.println("input error"); // level-5
        }else{
            String command = input.substring(0, spacepos);
            String rest=input.substring(spacepos+1);
            if (command.equals("done")) {
                String doneindex = rest;
                this.done(doneindex);
            }else if(command.equals("todo")){
                String taskdescript=rest;
                this.addtodo(taskdescript);
            }else if(command.equals("event")){
                String taskdescript=rest;
                this.addevent(taskdescript);
            }else if(command.equals("deadline")) {
                String taskdescript = rest;
                this.adddeadline(taskdescript);
            }else{
                System.out.println("command not recognized");  // level-5
            }
        }
    }
    private void done(String doneindex){
        try{
            int doneint=Integer.parseInt(doneindex);
            this.todolist.get(doneint-1).isdone();
        }catch(NumberFormatException e) {
            System.out.println("placeholder exception throw"); //done in level-5
        }
    }
    private void add(String task){
        Task t=new Task(task);
        this.todolist.add(t);
    }
    private void addtodo(String task){
        Todo td=new Todo(task);
        this.todolist.add(td);
        StringBuilder output=new StringBuilder("Task added:\n");
        output.append("    "+td);
        output.append("\n  There are "+this.todolist.size()+" tasks in the list.");
        Duke.print(output.toString());
    }
    private void addevent(String task){
        int split=task.indexOf(" /at");
        if(split==-1){
            System.out.println("Input error placeholder exception throw"); //done in level-5
        }else{
            String descript=task.substring(0, split);
            String deadline=task.substring(split+5);
            Event e=new Event(descript, deadline);
            this.todolist.add(e);
            StringBuilder output=new StringBuilder("Task added:\n");
            output.append("    "+e);
            output.append("\n  There are "+this.todolist.size()+" tasks in the list.");
            Duke.print(output.toString());
        }
    }
    private void adddeadline(String task){
        int split=task.indexOf(" /by");
        if(split==-1){
            System.out.println("Input error placeholder exception throw"); //done in level-5
        }else{
            String descript=task.substring(0, split);
            String deadline=task.substring(split+5);
            Deadline d=new Deadline(descript, deadline);
            this.todolist.add(d);
            StringBuilder output=new StringBuilder("Task added:\n");
            output.append("    "+d);
            output.append("\n  There are "+this.todolist.size()+" tasks in the list.");
            Duke.print(output.toString());
        }
    }
    public class Task{
        protected String description;
        protected boolean done;
        public Task(String descript){
            this.description=descript;
            this.done=false;
        }
        public void isdone(){
            this.done=true;
            String message="The following task has been marked as done:\n    "+this;
            Duke.print(message);
        }
        @Override
        public String toString(){
            if(done) {
                return "[" + "\u2713" + "]" + this.description;
            }else{
                return "[" + "\u2718" + "]" + this.description;
            }
        }
    }
    public class Todo extends Task{
        public Todo(String descript){
            super(descript);
        }
        @Override
        public String toString(){
            if(done) {
                return "[T]" + "[" + "\u2713" + "]" + this.description;
            }else{
                return "[T]" + "[" + "\u2718" + "]" + this.description;
            }
        }
    }
    public class Event extends Task{
        String deadline;
        public Event(String descript, String deadline){
            super(descript);
            this.deadline=deadline;
        }
        @Override
        public String toString(){
            if(done) {
                return "[E]" + "[" + "\u2713" + "]" + this.description + " (at: " + this.deadline + ")";
            }else{
                return "[E]" + "[" + "\u2718" + "]" + this.description + " (at: " + this.deadline + ")";
            }
        }
    }
    public class Deadline extends Task{
        String deadline;
        public Deadline(String descript, String deadline){
            super(descript);
            this.deadline=deadline;
        }
        @Override
        public String toString(){
            if(done) {
                return "[D]" + "[" + "\u2713" + "]" + this.description + " (by: " + this.deadline + ")";
            }else{
                return "[D]" + "[" + "\u2718" + "]" + this.description + " (by: " + this.deadline + ")";
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc= new Scanner(System.in);
        Duke process=new Duke();
        while(sc.hasNext()){
            String input=sc.nextLine();
            if(input.equals("bye")) {
                break;
            }else if(input.equals("list")){
                process.list();
            }else{
                process.run(input);
            }
        }
        String exitmsg="Bye. Hope to see you again.";
        print(exitmsg);
    }
}
