import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        //program start
        final String horizontalLine = "\t____________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(formatText("Hello! I'm Duke"));
        System.out.println(formatText("What can I do for you?"));
        System.out.println(horizontalLine);
        //import scanner + logic
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext("bye")){
            String command = scanner.next();
            System.out.println(horizontalLine);
            System.out.println(formatText(command));
            System.out.println(horizontalLine);
        }
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(horizontalLine);
        System.out.println(formatText(exitMessage));
        System.out.println(horizontalLine);
    }

    private static String formatText(String text){
        return "\t " + text;
    }
}
