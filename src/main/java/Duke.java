import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    static void printline(){
        String line =  "\t____________________________________________________________";
        System.out.println(line);
    }

    static void printnewline(){
        System.out.println("\n");
    }

    static void takeInput(String echo){
        printline();
        System.out.println("\tadded:" + echo);
        printline();
    }

    static void printList(LinkedList<Task> li){
        printline();
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < li.size(); i++){
            int j = i+1;
            System.out.println("\t" + j + ". " + li.get(i).taskCompletion());
        }
        printline();
    }

    static void printDone(Task task){
        printline();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task.taskCompletion());
        printline();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
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

        //LinkedList used to store all the String given by the user;
        LinkedList<Task> li = new LinkedList<Task>();
        while(true){
            printnewline();
            String echo = scan.next();

            if(echo.equals("bye")){
                printline();
                System.out.println("\tBye. Hope to see you again soon!");
                printline();
                break;
            } else if(echo.equals("list")){
                scan.nextLine();
                printList(li);
            } else if(echo.equals("done")){
                int taskNum = scan.nextInt();
                //scan.nextLine();
                System.out.println(taskNum);
                int taskNumb = taskNum - 1;
                Task change = li.get(taskNumb);
                change.completed();
                printDone(change);
            } else {
                String fullEcho = echo + scan.nextLine();
                Task newTask = new Task(fullEcho);
                li.add(newTask);
                takeInput(fullEcho);
            }
        }


    }
}
