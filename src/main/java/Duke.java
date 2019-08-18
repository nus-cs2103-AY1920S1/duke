import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (!input.equals("bye")) {
                echo(input);
            } else {
                exit();
                break;
            }
        }
    }

    public static void greet() {
        String message = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(message);
    }

    public static void echo(String s) {
        System.out.println(s);
    }

    public static void exit() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
    }
}

