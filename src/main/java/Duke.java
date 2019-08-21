import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static ArrayList<String> myList = new ArrayList<>();

    public static void printWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void printAdded(String command) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + command);
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        int counter = 1;
        for (String item : myList) {
            System.out.println("     " + counter + ". " + item);
            counter++;
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void main(String[] args) {
        printWelcome();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            if (currLine.equals("bye")) {
                printBye();
                sc.close();
                System.exit(0);
            } else if (currLine.equals("list")) {
                printList();
            } else {
                myList.add(currLine);
                printAdded(currLine);
            }
        }
    }
}
