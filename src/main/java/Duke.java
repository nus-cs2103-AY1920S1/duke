import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                    + "    |  _ \\ _   _| | _____ \n"
                    + "    | | | | | | | |/ / _ \\\n"
                    + "    | |_| | |_| |   <  __/\n"
                    + "    |____/ \\__,_|_|\\_\\___|\n";

        Duke duke = new Duke();
        printLine();
        System.out.println("    Hello! I'm\n" + logo + "\n" + "    What can I do for you?");
        printLine();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String text = sc.next();
            if (text.equals("bye")) {
                printBye();
                break;
            } else {
                echoFn(text);
            }
        }
    }

    public static void printIndent() {
        System.out.println("   ");
    }

    public static void printLine() {
        System.out.println("    ___________________________________________________________________");
    }

    public static void echoFn(String str) {
        printLine();
        System.out.println("    " + str);
        printLine();
    }

    public static void printBye() {
        printLine();
        System.out.println("    " + "Bye. Hope to see you again soon!");
        printLine();
    }
}
