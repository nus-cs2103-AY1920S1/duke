import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                    + "    |  _ \\ _   _| | _____ \n"
                    + "    | | | | | | | |/ / _ \\\n"
                    + "    | |_| | |_| |   <  __/\n"
                    + "    |____/ \\__,_|_|\\_\\___|\n";

        // Prints out greeting of the chatbot.
        printLine();
        printIndent();
        System.out.println("Hello! I'm\n" + logo + "\n" + "    What can I do for you?");
        printLine();

        Scanner sc = new Scanner(System.in);
        // String array to store text entered by user.
        String[] list = new String[100];
        // Counter to count total number of items in array.
        int n = 0;
        while (sc.hasNext()) {
            String text = sc.nextLine();
            if (text.equals("bye")) {
                printBye();
                break;
            } else if (text.equals("list")) {
                printLine();
                for (int i = 1; i <= n; i++) {
                    printIndent();
                    System.out.println(i + ". " + list[i-1]);
                }
                printLine();
            } else {
                addToList(list, text, n);
                printAdds(text);
                n++;
            }
        }
    }

    // Prints Indentation.
    private static void printIndent() {
        System.out.print("    ");
    }

    // Prints the line. For better organisation.
    private static void printLine() {
        printIndent();
        System.out.println("___________________________________________________________________");
    }

    // Adds text into the string array.
    private static void addToList(String[] list, String str, int num) {
        list[num] = str;
    }

    // Ends the chatbot.
    private static void printBye() {
        printLine();
        printIndent();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    // Shows users what has been added into the list/string array.
    private static void printAdds(String str) {
        printLine();
        printIndent();
        System.out.println("added: " + str);
        printLine();
    }
}
