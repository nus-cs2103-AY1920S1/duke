import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "    ____________________________________________________________";

        //Greetings message
        System.out.println(horizontalLine);
        System.out.println("     Hello from! I'm Duke\n" + "     What can I do for you?");
        System.out.println(horizontalLine + "\n");

        //Setup Scanner and List
        Scanner sc = new Scanner(System.in);
        List<String> store = new ArrayList<String>();

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(horizontalLine);
                int i = 1;
                for (String item : store) {
                    System.out.println("     "+ i + ". " + item);
                    i++;
                }
                System.out.println(horizontalLine + "\n");
            } else {
                store.add(userInput);
                System.out.println(horizontalLine);
                System.out.println("     " + "added: " + userInput);
                System.out.println(horizontalLine + "\n");
            }
            userInput = sc.nextLine();
        }

        System.out.println(horizontalLine);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);

    }
}
