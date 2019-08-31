import java.util.Scanner;

/** This class handles the user interface */
public class UserInterface {
    private static Scanner sc;

    public UserInterface() {
        sc = new Scanner(System.in);
    }

    /** Shows error about why file cannot be read. */
    public void showLoadingError(Exception e) {
        System.out.println(e);
        System.out.println(e.getCause());
    }

    /** Main event loop. */
    public void run() {
        boolean resume = true;
        String in;
        while (resume) {
            try {
                in = sc.next();
            } catch (Exception e) {
                break; // input stream has ended.
            }
            try {
                resume = Parser.handleCommand(in, sc);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}
