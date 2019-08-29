package main;

/**
 * Helper class for the user
 */
public class Helper {

    private static int NUMBER_OF_COMMANDS = 9;
    String[] arr;

    /**
     * Constructs a new Helper object
     */
    public Helper() {
        arr = new String[NUMBER_OF_COMMANDS+1];
        arr[0] = "Here are the list of commands and their allowable input formats";
        arr[1] = "bye";
        arr[2] = "list";
        arr[3] = "done <task ID>";
        arr[4] = "delete <task ID>";
        arr[5] = "todo <task name>";
        arr[6] = "event <task name> / dd/MM/yyyy HHmm";
        arr[7] = "deadline <task name> / dd/MM/yyyy HHmm";
        arr[8] = "help";
        arr[9] = "clear";
    }

    /**
     * Returns an array of allowable input formats
     *
     * @return An array with the list of allowable input formats
     */
    public String[] getCommands() {
        return arr;
    }


}
