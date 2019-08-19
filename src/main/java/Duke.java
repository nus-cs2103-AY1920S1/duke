import java.util.Scanner;

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
        System.out.println("\t" + echo);
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

        while(true){
            printnewline();
            String echo = scan.nextLine();
            if(echo.equals("bye")){
                printline();
                System.out.println("\tBye. Hope to see you again soon!");
                printline();
                break;
            } else {
                takeInput(echo);
            }
        }


    }
}
