import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\n");
        System.out.println("How may I help you?\n");
        System.out.println("------------------------------\n");
        while (true) {
            String input = sc.nextLine();
            System.out.println("\n------------------------------");
            if (input.equals("bye")) {
                System.out.println("    Bye! See you again soon!!\n");
                System.out.println("------------------------------");
                break;
            } else {
                System.out.println("    " + input + "\n");
                System.out.println("------------------------------");
            }
        }
    }
}
