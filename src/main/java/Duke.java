import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Represents Duke, a personal chatbot assistant.
 */
public class Duke {

    private ArrayList<String> list;

    public Duke() {
        this.list = new ArrayList<>();
    }

    public ArrayList<String> getList() {
        return this.list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    // print out all the commands as a numbered list
    private void printList() {
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println("\t" + i + ". " + this.list.get(i - 1));
        }
    }

    // print hello message
    private void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String openingMessage = "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n";
        System.out.println(openingMessage);
    }

    // execute command given depending on what command it is
    private void execCommand(String command) {
        if (command.equals("list")) {
            printList();
        } else {
            this.list.add(command);
            System.out.println("\tadded: " + command);
        }
    }

    private void printExitMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.hello();

        //take in user input and print according to user command
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine().trim();
            if (command.equals("bye")) { //user wants to exit
                duke.printExitMessage(); //program terminates
                return;
            } else {
                duke.execCommand(command);
            }
        }
    }

}
