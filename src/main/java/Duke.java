package duke;

/**
 * Application class for Duke.
 */
public class Duke {
    /**
     * Main method for Duke.
     * 
     * @param args Arguments entered when main method is executed.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Main main = new Main("../../../data/duke.txt");
        main.run();
    }
}
