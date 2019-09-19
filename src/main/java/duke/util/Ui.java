package duke.util;

public class Ui {

    public Ui() {
    }

    public static String showWelcome() {
        String message = String.format("Hello! I'm Nick.%sWhat can I OOP for you today?", System.lineSeparator());
        return message;
    }

    public static String showLoading(int size) {
        String message = (size + (size == 1 ? "task" : " tasks") + " were loaded successfully.");
        return message;
    }

    public static String showError(String message) {
        return message;
    }

    public static String showBye() {
        return "Goodbye... Until more OOP.";
    }

    public static String showHelp() {
        String message = String.format("Hello! I'm Nick! You can use:%stodo <item>%sdeadline <item> /by <when>"
                        + "%sevent <item> /at <when>%sfind <keyword>%sdone <number>%sdelete <number>%sstats%sbye", System.lineSeparator(), System.lineSeparator(),
                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator());
        return message;
    }

}
