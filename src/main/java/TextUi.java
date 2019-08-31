import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


/**
 * TextUi of the Application
 */

public class TextUi {

    /** Duke Logo to display upon startup. */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

}
