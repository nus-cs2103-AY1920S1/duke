import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(welcomeMessage);
        Scanner reader = new Scanner(System.in);
        while (true) {
            String currentLine = reader.nextLine();
            if (currentLine.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else {
                System.out.println(currentLine);
            }
        }
    }
}
