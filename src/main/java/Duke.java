import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        //program start
        final String horizontalLine = "\t____________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println(horizontalLine);
        //import scanner + logic
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext("bye")){
            String command = scanner.next();
            System.out.println(horizontalLine);
            System.out.println("\t"+command);
            System.out.println(horizontalLine);
        }
        String exitMessage = "\tBye. Hope to see you again soon!";
        System.out.println(horizontalLine);
        System.out.println(exitMessage);
        System.out.println(horizontalLine);
    }
}
