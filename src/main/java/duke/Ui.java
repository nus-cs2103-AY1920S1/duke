package duke;

import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    public Ui(){
        printOutput("Hello! I'm duke.Duke\nWhat can i do for you?");
    }

    public String readCommand(){
        return sc.nextLine();
    }
    public void printExit(){
        printOutput("Bye. Hope to see you again soon!");
    }

    public void printOutput(String s){
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + s.replace("\n","\n    "));
        System.out.println("    ____________________________________________________________");
    }

    public void printOutput(String s, String taskMessage, int listSize){
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + taskMessage);
        System.out.println("    " + s.replace("\n","\n    "));
        System.out.println("    " + "Now you have " + listSize + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }
}
