import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String BYE = "bye";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        String inputString = null;
        while (!(Objects.equals(inputString, BYE))) {
            inputString = scanner.nextLine();
            if (!(Objects.equals(inputString, BYE))) {
                System.out.println("\t\t" + inputString + "\n");
            } else {
                System.out.println("\t\tBye. Hope to see you again soon!\n");
            }
        }
    }
}
