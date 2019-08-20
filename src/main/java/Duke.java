import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void printIndentedMsg(String msg){
        String indent= "     ";
        System.out.println(indent + msg);
    }

    private static void printLine(){
        String line= "    ____________________________________________________________";
        System.out.println(line);
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        printLine();
        printIndentedMsg("Hello! I'm Duke");
        printIndentedMsg("What can I do for you?");
        printLine();
        String msg = s.nextLine();
        ArrayList<String> toDoList = new ArrayList<>();
        while(!msg.equals("bye")) {
            switch (msg) {
                case "list":
                    printLine();
                    int startNumber = 1;
                    for(String str : toDoList){
                        printIndentedMsg("" + startNumber + ". " + str);
                        startNumber++;
                    }
                    printLine();
                    break;
                default:
                    printLine();
                    printIndentedMsg("added: " + msg);
                    toDoList.add(msg);
                    printLine();
            }
            msg = s.nextLine();
        }
        printLine();
        printIndentedMsg("Bye. Hope to see you again soon!");
        printLine();
    }
}
