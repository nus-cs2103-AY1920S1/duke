import java.util.Scanner;

public class Duke {
    private String[] list;

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Hello I'm Duke\nWhat can I do for you\n");
        while (true) {
            Duke duke = new Duke();
            String command = sc.nextLine();
            if (duke.handleCommand(command)) {
                break;   
            }
        }
    }

    /**
     * Constructor.
     */
    public Duke() {
    }

    /**
     * Handles the various commands.
     * @param command String
     * @return boolean
     */
    boolean handleCommand(String command) {
        switch (command) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        default:
            System.out.println(command);
            return false;
        }
    }
}
