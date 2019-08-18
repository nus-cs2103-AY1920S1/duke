import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Prints a block which contains all the lines given.
     * @param text a list of lines to be printed inside the block
     */
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

        ArrayList<Task> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                printBlock("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                String[] text = new String[list.size() + 1];
                text[0] = "Here are the tasks in your list:";
                for (int i = 0; i < list.size(); i++) {
                    text[i + 1] = (i + 1) + "." + list.get(i);
                }
                printBlock(text);
            } else {
                String[] words = command.split(" ");
                if (words[0].equals("done")) {
                    int index = Integer.valueOf(words[1]) - 1;
                    list.get(index).setDone();
                    printBlock("Nice! I've marked this task as done:", "  " + list.get(index));
                } else {
                    list.add(new Task(command));
                    printBlock("added: " + command);
                }
            }
        }
    }

}