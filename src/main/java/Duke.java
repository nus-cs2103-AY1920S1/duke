import java.util.Scanner;

public class Duke {
    public static String frontSpace = "    ";
    public static String message;
    public static String upperLine = "____________________________________________________________\n";
    public static String lowerLine = "____________________________________________________________\n";

    public static void byeFeature() {
        System.out.print(frontSpace + upperLine);
        message = " Bye. Hope to see you again soon!\n";
        System.out.print(frontSpace + message);
        System.out.println(frontSpace + lowerLine);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greet = "     Hello! I'm Duke\n"
                + "     What can I do for you?\n";
        greet = frontSpace + upperLine + greet + frontSpace + lowerLine;
        System.out.println(greet);

        while (true) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                byeFeature();
                break;
            } else {
                System.out.print(frontSpace + upperLine);
                message = " " + cmd;
                System.out.println(frontSpace + message);
                System.out.println(frontSpace + lowerLine);
            }
        }
    }
}