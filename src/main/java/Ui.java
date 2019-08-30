import tasks.Task;

import java.util.LinkedList;

public class Ui {
    public Ui(){

    }

    public void printline(){
        String line =  "\t____________________________________________________________";
        System.out.println(line);
    }

    //print new line for formatting
    public void printnewline(){
        System.out.println("\n");
    }

    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String name = "Duke";
        printline();
        System.out.println("\tHello, I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printline();
    }

        //gives confirmation that an task input is taken
        public void takeInput(Task t, int i){
            printline();
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t\t" + t);
            System.out.println("\tNow you have " + i + " tasks in the list");
            printline();
        }
    
        //prints out the list of task on hand
        public void printList(LinkedList<Task> li){
            printline();
            System.out.println("\tHere are the tasks in your list:");
            for(int i = 0; i < li.size(); i++){
                int j = i+1;
                System.out.println("\t" + j + ". " + li.get(i));
            }
            printline();
        }
    
        //completion of task confirmation
        public void printDone(Task task){
            printline();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t" + task);
            printline();
        }
    
        //completion of removal of task
        public void printDelete(Task task, int i){
            printline();
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t\t" + task);
            System.out.println("\tNow you have " + i + " tasks in the list");
            printline();
        }


}