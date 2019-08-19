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

    static void printList(LinkedList<String> li){
        printline();
        for(int i = 0; i < li.size(); i++){
            int j = i+1;
            System.out.println("\t" + j + ". " + li.get(i));
        }
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
        LinkedList<String> li = new LinkedList<String>();
        while(true){
            printnewline();
            String echo = scan.nextLine();
            if(echo.equals("bye")){
                printline();
                System.out.println("\tBye. Hope to see you again soon!");
                printline();
                break;
            } else if(echo.equals("list")){
                printList(li);
            }else {
                li.add(echo);
                takeInput(echo);
            }
        }


    }
}
