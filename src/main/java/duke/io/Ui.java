package duke.io;

/**
 * The Ui class provides some methods for I/O in the duke.ui.Duke program. This includes greetings,
 * good-byes, success and error messages and a general formatting function.
 */
public class Ui {
    /**
     * Returns a String containing the program's welcome message.
     *
     * @return a new String containing the program's welcome message
     */
    public String showWelcome() {
        String logoOne = " ____        _        ";
        String logoTwo = "|  _ \\ _   _| | _____ ";
        String logoThree = "| | | | | | | |/ / _ \\";
        String logoFour = "| |_| | |_| |   <  __/";
        String logoFive = "|____/\\__,_|_|\\_\\___|\n";

        String greeting = "Welcome to";
        String question = "What can I do for you today?";
        return asDukeMessage(greeting, logoOne, logoTwo, logoThree, logoFour, logoFive, question);
    }

    /**
     * Returns a String containing an error message if loading from storage succeeds.
     *
     * @return a new String containing an error message
     */
    public String showLoadingError() {
        return asDukeMessage("No Task list found! Creating new Task list...");
    }

    /**
     * Returns a String containing a success message if loading from storage succeeds.
     *
     * @return a new String containing a success message
     */
    public String showLoadingSuccess() {
        return asDukeMessage("Saved tasks successfully loaded.");
    }

    public String showHelper() {
        return asDukeMessage(
                "I don't know what that means! For help: type \"help\" for more information.");
    }

    /**
     * Returns a String containing a detailed help message.
     *
     * @return a String containing a detailed help message.
     */
    public String showDetailedHelp() {
        String[] text = {
            "Here are a list of commands you can try:",
            "",
            "list\t\tShow list of tasks",
            "todo\t\t[DESCRIPTION]",
            "deadline\t[DESCRIPTION] /by\t[TIME]",
            "event\t[DESCRIPTION] /at\t[TIME]",
            "done\t[NUMBER]",
            "delete\t[NUMBER]",
            "find\t\t[SEARCH STRING]",
            "",
            "[TIME]: [dd/mm/yyyy hh:mm]"
        };
        return asDukeMessage(text);
    }

    /**
     * Returns a String containing the elements of beforeText, args then afterText. Each individual
     * String is delimited with a newline character.
     *
     * @param args strings to be printed
     * @return a String containing the elements in the param following the above rules
     */
    public String asDukeMessage(String[] beforeText, String[] afterText, String... args) {
        StringBuilder ret = new StringBuilder();
        for (String s : beforeText) {
            ret.append(s).append("\n");
        }
        for (String s : args) {
            ret.append(s).append("\n");
        }
        for (String s : afterText) {
            ret.append(s).append("\n");
        }
        return ret.toString();
    }

    /**
     * Returns a String containing the elements of args. Each individual String is delimited with
     * a newline character.
     *
     * @param args strings to be printed
     * @return a String containing the elements in the param following the above rules
     */
    public String asDukeMessage(String... args) {
        return asDukeMessage(new String[]{}, new String[]{}, args);
    }
}
