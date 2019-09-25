import java.util.Scanner;

/**
 * Represents the interaction between user and Duke.
 */
public class Ui {

    /**
     * Starts Duke and sends command to parser.
     * @param tasks Task object.
     * @param storage Storage object.
     */
    public void run(TaskList tasks, Storage storage) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Parser parser = new Parser(storage, tasks);
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                String command = sc.nextLine();
                if (command.equals("hello")) {
                    System.out.println("Hello! I'm Duke. " + "\n" + "What can I do for you?");
                } else if (command.equals("bye")) {
                    System.out.println("Bye. See you again. ");
                    System.exit(0);
                } else {
                    System.out.println(parser.read(command));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("File not found");
        }
    }

    /**
     * Prints if file not available.
     */
    public String showLoadingError() {
        //System.out.println("File not available");
        return "File not available";
    }

}
