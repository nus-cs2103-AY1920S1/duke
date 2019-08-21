import java.util.Scanner;

public class Duke {
    public static String[] data = new String[100];

    public static void printLine() {
        System.out.println("   ____________________________________________________________");
    }

    public static void printGreet() {
        printLine();
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printLine();
        System.out.println("");
    }

    public static void readInput() {
        Scanner input = new Scanner(System.in);
        int item = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();

            if (line.equals("bye")) {
                printLine();
                System.out.println("    Bye. Hope to see you again soon!");
                printLine();
                System.out.println("");
                break;
            } else if (line.equals("list")) {
                int index = 1;
                printLine();
                for (int i = 0; i < item; i++) {
                    System.out.println("    " + index++ + "." + data[i]);
                }
                printLine();
                System.out.println("");
            } else {
                data[item] = line;
                printLine();
                System.out.println("    " + "added: " + line);
                printLine();
                System.out.println("");
                item++;
            }
        }
    }

    public static void main(String[] args) {
        printGreet();
        readInput();
    }
}