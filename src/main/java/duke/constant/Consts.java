package duke.constant;

import java.text.SimpleDateFormat;

/**
 * Represents a class of constants that is used within the application.
 */
public final class Consts {

    /**
     * Logo of Duke application.
     */
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    //To prevent users from creating an instance of this class
    private Consts() {
        throw new AssertionError();
    }

    /**
     * Format of input of date time that the user is supposed to follow.
     */
    public static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Format of date time that is to be stored in the file.
     */
    public static final SimpleDateFormat DATE_TIME_OUTPUT_FORMATTER = new SimpleDateFormat("d LLL yyyy h:mm a");
}
