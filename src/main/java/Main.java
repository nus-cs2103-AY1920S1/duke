import duke.Duke;
import duke.exception.DukeIOException;

public class Main {

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        try {
            (new Duke()).run();
        } catch (DukeIOException e) {
            System.err.println("An IOException was caught: " + e.getMessage());
        }
    }

}
