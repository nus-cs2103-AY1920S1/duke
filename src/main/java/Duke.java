import java.util.Scanner;

public class Duke {
    private String[] list;
    private int currentIndex;

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Hello I'm Duke\nWhat can I do for you\n");
        Duke duke = new Duke();
        while (true) {
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
        this.list = new String[100];
        this.currentIndex = 0;
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
        case "list":
            for (int i = 0; i < this.currentIndex; i++) {
                System.out.printf("    %d. %s\n", i + 1, this.list[i]);
            }
            break;
        default:
            this.list[this.currentIndex++] = command;
            System.out.printf("    added: %s\n", command);
        }
        return false;
    }
}
