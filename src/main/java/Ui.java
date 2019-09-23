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
    /*public void run (TaskList tasks, Storage storage) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        this.outputHolder.append("Hello! I'm Duke. What can I do for you?");
        Parser parser = new Parser(storage, tasks);
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                String command = sc.next();
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    this.outputHolder.append("Bye. Hope to see you again soon!");
                    break;
                } else {
                    parser.read(command, sc);
                }
            }
        } catch(Exception ex) {
            this.outputHolder.append("File not found");
            System.out.println("File not Found");
        }
    }
*/
    public void run (TaskList tasks, Storage storage) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Parser parser = new Parser(storage, tasks);
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                String command = sc.nextLine();
                if (command.equals("hello")) {
                    System.out.println("Hello! I'm Duke. " + "\n" + "What can I do for you?");
                }
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                } else {
                    System.out.println(parser.read(command));
                }
            }
        } catch(Exception ex) {
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

    /*public void displayLine(String toReply) {
        this.outputHolder.append(toReply);
    }*/

    /**
     * Returns the required output to the user.
     * @return a String representation of the response.
     */
    /*public String getOutput() {
        String output = this.outputHolder.toString();
        resetUI();
        return output;
    }*/

    /**
     * Sets UI back to default phase, waiting for user input.
     */
    /*public void resetUI() {
        this.outputHolder = new StringBuilder();
    }*/
}
