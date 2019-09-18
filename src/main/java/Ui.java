import java.util.Scanner;

/**
 * Represents the interaction betweeen user and Duke.
 */
public class Ui {

    /**
     * Starts Duke and sends command to parser.
     * @param tasks Task object.
     * @param storage Storage object.
     */
    public void run (TaskList tasks, Storage storage) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Parser parser = new Parser(storage, tasks);
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                String command = sc.next();
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else {
                    parser.read(command, sc);
                }
            }
        } catch(Exception ex) {
            System.out.println("File not Found");
        }
    }

    /**
     * Prints if file not available.
     */
    public void showLoadingError() {
        System.out.println("File not available");
    }
}
