import java.util.*;

public class Duke {

    public static ArrayList<String> storage = new ArrayList<>();
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */

        printOutput("Hello! I'm Duke\nWhat can i do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            if(input.equals("list")){
                String listOutput = "";
                for(int i = 0; i < storage.size(); i++){
                    listOutput += (i+1) + ". " + storage.get(i);
                    if(i+1 != storage.size()){
                        listOutput += "\n";
                    }
                }
                printOutput(listOutput);
            }
            else {
                storage.add(input);
                printOutput("added: " + input);
            }
            input = sc.nextLine();
        }
        printOutput("Bye. Hope to see you again soon!");
    }

    private static void printOutput(String s){
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + s.replace("\n","\n    "));
        System.out.println("    ____________________________________________________________");
    }
}
