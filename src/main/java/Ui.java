import java.util.Calendar;
import java.util.Scanner;

public class Ui {
    public static final String LINE = "    ____________________________________________________________\n";

    public void greet() {
        String greeting = LINE
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + LINE;

        System.out.println(greeting);
    }

    public void getUserInput(Parser parser) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        parser.parse(userInput);
    }

}
