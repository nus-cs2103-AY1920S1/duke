import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> userinputs = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Greet user
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // Ask initial user input
        String userinput = scanner.nextLine();

        while (!userinput.equals("bye")) {
            if (userinput.equals("list")) {
                // Output current items in list
                for (int i = 0; i < userinputs.size(); i++) {
                    int currentItemNumber = i + 1;
                    String currentUserinput = userinputs.get(i);
                    System.out.println(currentItemNumber + ". " + currentUserinput);
                }
            }
            else {
                // Add user input to list and output
                userinputs.add(userinput);
                System.out.println("added: " + userinput);
            }
            // Ask for next userinput
            userinput = scanner.nextLine();
        }

        // At this point userinput equals "bye"
        System.out.println("Bye. Hope to see you again soon!");
    }
}
