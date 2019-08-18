import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static void printBlock(String... text) {
        String horizontalLine = "____________________________________________________________";
        String indentation = "    ";
        System.out.println(indentation + horizontalLine);
        for (String line: text) {
            System.out.println(indentation + " " + line);
        }
        System.out.println(indentation + horizontalLine + "\n");
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke";
        String question = "What can I do for you?";
        printBlock(greeting, question);

        ArrayList<String> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                printBlock("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                String[] text = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    text[i] = (i + 1) + ". " + list.get(i);
                }
                printBlock(text);
            } else {
                list.add(command);
                printBlock("added: " + command);
            }
        }
    }

}