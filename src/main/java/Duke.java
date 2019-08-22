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
        if (spacepos == -1) {  //will change in level-4
            this.add(input);
        }else{
            String command = input.substring(0, spacepos);
            if (command.equals("done")) {
                String doneindex = input.substring(spacepos + 1);
                this.done(doneindex);
            }else{
                this.add(input);
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
