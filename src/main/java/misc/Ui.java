package misc;

/**
 * Creates a User Interface that interacts with and displays information to the user.
 */
public class Ui {
    static final String HORIZONTAL_LINE = spaces(5) + "____________________________________________________________\n";
    
    private String dukeLogo = spaces(5) + " ____        _        \n"
                + spaces(5) + "|  _ \\ _   _| | _____ \n"
                + spaces(5) + "| | | | | | | |/ / _ \\\n"
                + spaces(5) + "| |_| | |_| |   <  __/\n"
                + spaces(5) + "|____/ \\__,_|_|\\_\\___|\n";

    public static void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static String spaces(int n) {
        String s = " ";
        return s.repeat(n);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void welcome() {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(dukeLogo);

        String welcomeMessage = String.format("%sHello! I'm Duke!\n%sWhat can I do for you?",
                spaces(5), spaces(5));

        System.out.println(welcomeMessage);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a welcomeBack message to the user.
     * This occurs if there are existing tasks in a local save file from last session.
     */
    public void welcomeBack() {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(dukeLogo);

        String welcomeMessage = String.format("%sHello! Welcome back!\n", spaces(5))
                + String.format("%sCarrying off from where you left behind the last time...", spaces(5));

        System.out.println(welcomeMessage);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.printf("%sBye! Hope to see you again soon! \u263A\n", spaces(5));
        System.out.println(HORIZONTAL_LINE);
    }
}
