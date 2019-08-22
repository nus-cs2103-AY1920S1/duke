import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected static String[] list = new String[100];
    protected static ArrayList<Task> arrayList = new ArrayList<Task>();

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
        //String[] list = new String[100];
        // Counter to count total number of items in array.
        int n = 0;

        //Task task = new Task();

        while (sc.hasNext()) {
            String text = sc.nextLine();
            Task task = new Task(text);
            if (text.equals("bye")) {
                printBye();
                break;
            } else if (text.equals("list")) {
                printLine();
                for (int i = 1; i <= n; i++) {
                    printIndent();
                    System.out.println(i + "." + arrayList.get(i-1).getStatusIcon() + " " + list[i - 1]);
                }
                printLine();
            } else if (text.indexOf(" ") > -1 &&
                    (text.substring(0, text.indexOf(" "))).equals("done")) {
                //Task.checklist[n] = "done";
                int num = text.indexOf(" ");
                printDone(Integer.parseInt(text.substring(num+1, num+2)));
            } else {
                addToList(text, n);
                printAdds(text);
                n++;
            }
            arrayList.add(task);
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
    private static void addToList(String str, int num) {
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

    private static void printDone(int i) {
        arrayList.get(i-1).markAsDone();
        printLine();
        printIndent();
        System.out.println("Nice! I've marked this task as done:");
        printIndent();
        System.out.println("  " + arrayList.get(i-1).getStatusIcon() + " " + list[i-1]);
        printLine();
    }

}
