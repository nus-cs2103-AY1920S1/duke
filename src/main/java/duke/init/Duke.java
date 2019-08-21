package duke.init;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Lim Yong Shen, Kevin
 */
public class Duke {

    private static final int BORDER_LENGTH = 60;
    private static final ArrayList<String> storedText = new ArrayList<>();

    /**
     * Runs the Duke chatbot.
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printHorizontalBorder();
        greet();
        printHorizontalBorder();
        System.out.println();
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            printHorizontalBorder();
            process(command);
            printHorizontalBorder();
            System.out.println();
            command = scanner.nextLine();
        }
        printHorizontalBorder();
        sayBye();
        printHorizontalBorder();
        scanner.close();
    }

    /**
     * Greets the user.
     */
    private static void greet() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);
        System.out.println("\tWhat can I do for you?");
    }

    /**
     * Echos the specified command.
     * @param command The specified command.
     */
    private static void echo(String command) {
        System.out.println("\t" + command);
    }

    /**
     * Processes the specified command.
     * @param command The specified command.
     */
    private static void process(String command) {
        if (command.equals("list")) {
            listStoredText();
        } else {
            storeText(command);
        }
    }

    /**
     * Lists stored text.
     */
    private static void listStoredText() {
        if (storedText.size() == 0) {
            System.out.println("No stored text.");
        } else {
            for (int i = 0; i < storedText.size(); i++) {
                System.out.format("%d. %s\n", i + 1, storedText.get(i));
            }
        }
    }

    /**
     * Stores the specified text.
     * @param text The specified text.
     */
    private static void storeText(String text) {
        System.out.println("added: " + text);
        storedText.add(text);
    }

    /**
     * Says bye to the user.
     */
    private static void sayBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Prints a horizontal border.
     */
    private static void printHorizontalBorder() {
        StringBuilder border = new StringBuilder("\t");
        for (int i = 0; i < BORDER_LENGTH; i++) {
            border.append('_');
        }
        System.out.println(border);
    }

}

