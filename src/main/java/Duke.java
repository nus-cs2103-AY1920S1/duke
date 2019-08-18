public class Duke {

    public static final String LONG_LINE = "____________________________________________________________";
    public static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";

    public static void main(String[] args) {
        printGreeting();
    }

    public static void printGreeting() {
        System.out.println(
            LONG_LINE
            + "\n"
            + GREETING
            + "\n"
            + LONG_LINE
            + "\n"
        );
    }
}
