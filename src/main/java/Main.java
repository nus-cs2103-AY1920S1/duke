import java.util.Scanner;

/**
 * Driver class for Duke, a personal assistant chatbot.
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 2
 */
public class Main {
    /**
     * The main method for Duke.
     *
     * Creates a new instance of Duke, asks Duke to greet user, and then reads
     * data from stdin. This input is then handed over to Duke for processing.
     *
     * @param args command(s) from the user
     */
    public static void main(String[] args) {
        // create an instance of Duke and let Duke greet the user
        Duke duke = new Duke();
        duke.greet();

        // scan for commands from the over and send them to Duke for processing
        Scanner scanner = new Scanner(System.in);
        while (duke.getIsListening()) {
            duke.processCommand(scanner.nextLine());
        }
    }
}