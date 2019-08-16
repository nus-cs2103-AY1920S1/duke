import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<String> tasks = new ArrayList<String>();
        
        printPrompt("Hello! I'm Duke");
        printPrompt("What can I do for you?");
        
        while (true) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                break;
            } else if (cmd.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    printPrompt((i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(cmd);
                printPrompt("added: " + cmd);
            }
        }
        
        printPrompt("Bye. Hope to see you again soon!");
    }
}
