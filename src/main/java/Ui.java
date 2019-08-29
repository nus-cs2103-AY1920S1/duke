public class Ui {   
    static final String HORIZONTAL_LINE = spaces(5) + "____________________________________________________________\n";
    
    static String dukeLogo = spaces(5) + " ____        _        \n"
                + spaces(5) + "|  _ \\ _   _| | _____ \n"
                + spaces(5) + "| | | | | | | |/ / _ \\\n"
                + spaces(5) + "| |_| | |_| |   <  __/\n"
                + spaces(5) + "|____/ \\__,_|_|\\_\\___|\n";

    static void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    static String spaces(int n) {
        StringBuilder sb = new StringBuilder();

        for (int count = 0; count < n; count++) {
            sb.append(" ");
        }

        return sb.toString();
    }

    void welcome() {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(dukeLogo);

        String welcomeMessage = String.format("%sHello! I'm Duke!\n%sWhat can I do for you?",
                spaces(5), spaces(5));

        System.out.println(welcomeMessage);
        System.out.println(HORIZONTAL_LINE);
    }

    void welcomeBack() {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(dukeLogo);

        String welcomeMessage = String.format("%sHello! Welcome back!\n", spaces(5))
                + String.format("%sCarrying off from where you left behind the last time...", spaces(5));

        System.out.println(welcomeMessage);
        System.out.println(HORIZONTAL_LINE);
    }

    void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.printf("%sBye! Hope to see you again soon! \u263A\n", spaces(5));
        System.out.println(HORIZONTAL_LINE);
    }
}