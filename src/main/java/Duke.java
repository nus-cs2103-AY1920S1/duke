import java.util.*;
class DukeException extends Exception{
    public DukeException(String s){
        super(s);
    }
}
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
        input=input.trim();
        int spacepos = input.indexOf(" ");
        if (spacepos == -1) {  //raise exception: only 1 word given
            try{
                this.findandraisesingle(input);
            }catch(DukeException e){
                Duke.print(e.getMessage());
            }
        }else{
            String command = input.substring(0, spacepos);
            String rest=input.substring(spacepos+1);
            if (command.equals("done")) {
                this.done(rest);
            }else if(command.equals("delete")){
                this.delete(rest);
            }else if(command.equals("todo")){
                this.addtodo(rest);
            }else if(command.equals("event")){
                this.addevent(rest);
            }else if(command.equals("deadline")) {
                this.adddeadline(rest);
            }else{
                Duke.print("Error: input not recognized");  //
            }
        }
    }
    private void done(String doneindex){
        try{
            int doneint=Integer.parseInt(doneindex);
            this.todolist.get(doneint-1).isdone();
        }catch(NumberFormatException e) {
            Duke.print("Error: bad task index"); // for wrong index provided
        }catch(IndexOutOfBoundsException e){
            Duke.print("Error: no such task index");  //for index>array length
        }
    }
    private void delete(String deleteindex){  //exception same as done method
        try{
            int delindex=Integer.parseInt(deleteindex);
            StringBuilder outmsg=new StringBuilder("Following task removed:\n    " + this.todolist.get(delindex-1));
            outmsg.append("\n  " + (this.todolist.size()-1) + " tasks left in the list");
            this.todolist.remove(delindex-1);
            Duke.print(outmsg.toString());
        }catch(NumberFormatException e) {
            Duke.print("Error: bad task index");
        }catch(IndexOutOfBoundsException e){
            Duke.print("Error: no such task index");
        }
    }
    private void addtodo(String task){
        task=task.trim();
        Todo td=new Todo(task);
        this.todolist.add(td);
        StringBuilder output=new StringBuilder("Task added:\n");
        output.append("    "+td);
        output.append("\n  There are "+this.todolist.size()+" tasks in the list");
        Duke.print(output.toString());
    }
    private void addevent(String task){
        int split=task.indexOf(" /at");
        if(split==-1){
            Duke.print("Error: event time not given. Specify event time using \"/at\""); //throw exception?
        }else{
            try{
                String descript = task.substring(0, split);
                String deadline = task.substring(split + 5);  //exception may occur
                descript=descript.trim();
                deadline=deadline.trim();
                Event e = new Event(descript, deadline);
                this.todolist.add(e);
                StringBuilder output = new StringBuilder("Task added:\n");
                output.append("    " + e);
                output.append("\n  There are " + this.todolist.size() + " tasks in the list.");
                Duke.print(output.toString());
            }catch(IndexOutOfBoundsException e){ // happens when input is "event xx /at" with no time given
                Duke.print("Error: no event time provided");
            }
        }
    }
    private void adddeadline(String task){
        int split=task.indexOf(" /by");
        if(split==-1){
            Duke.print("Error: deadline not given. Specify deadline using \"/by\""); // i dont know abt this
        }else{
            try {
                String descript = task.substring(0, split);
                String deadline = task.substring(split + 5);
                descript=descript.trim();
                deadline=deadline.trim();
                Deadline d = new Deadline(descript, deadline);
                this.todolist.add(d);
                StringBuilder output = new StringBuilder("Task added:\n");
                output.append("    " + d);
                output.append("\n  There are " + this.todolist.size() + " tasks in the list.");
                Duke.print(output.toString());
            }catch(IndexOutOfBoundsException e){ //same as event time
                Duke.print("Error: no deadline provided");
            }
        }
    }
    private void findandraisesingle(String badinput) throws DukeException{
        if(badinput.equals("todo")||badinput.equals("event")||badinput.equals("deadline")){
            throw new DukeException("Error: no description for task.");
        }else if(badinput.equals("done")){
            throw new DukeException("Error: done task index missing");
        }else{
            throw new DukeException("Error: no such command");
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
        String exitmsg="Goodbye. Hope to see you again.";
        print(exitmsg);
    }
}
