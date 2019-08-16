import java.util.Scanner;

public class Duke {
    //@@author Parcly-Taxel
    /**
      * Pretty-prints a line of prompt to the user.
      */
    public static void printPrompt(String prompt) {
        System.out.println("    " + prompt);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printPrompt("Hello! I'm Duke");
        printPrompt("What can I do for you?");
        
        while (true) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                break;
            }
            printPrompt(cmd);
        }
        
        printPrompt("Bye. Hope to see you again soon!");
    }
}
