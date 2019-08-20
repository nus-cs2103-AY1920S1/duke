import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> myList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printMessage("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                StringBuilder myBuilder = new StringBuilder();
                for (int i = 1; i <= myList.size(); i++) {
                    myBuilder.append(i + ". " + myList.get(i - 1));
                    if (i < myList.size()) {
                        myBuilder.append("\n");
                    }
                }
                printMessage(myBuilder.toString());
            } else {
                printMessage("added: " + input);
                myList.add(input);
            }
        }
        in.close();
    }

    private static void printMessage(String message) {
        String[] messages = message.split("\n");
        System.out.println("    ____________________________________________________________");
        for (String line : messages) {
            System.out.println("     " + line);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }
}
