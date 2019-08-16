import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(welcomeMessage);
        Scanner reader = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            String currentLine = reader.nextLine();
            if (currentLine.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (currentLine.equals("list")) {
                int count = 1;
                for (String s: list) {
                    System.out.println("" + count + ". " + s);
                    count++;
                }
            } else {
                list.add(currentLine);
                System.out.println("added: " + currentLine);
            }
        }
    }
}