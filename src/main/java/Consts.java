import java.text.SimpleDateFormat;

public final class Consts {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    //To prevent users from creating an instance of this class
    private Consts() {
        throw new AssertionError();
    }

    public static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HHmm");
    public static final SimpleDateFormat DATE_TIME_OUTPUT_FORMATTER = new SimpleDateFormat("d LLL yyyy h:mm a");
}
