import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private final String line = "\t____________________________________________________________";

    Ui() {
        sc = new Scanner(System.in);
    }

    void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        dukePrint("Hello from\n" + logo + "\nWhat can I do for you?");
    }

    void dukePrint(String s) {
        System.out.println(line);
        String[] arr = s.split("\n");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("\t " + arr[i]);
        }
        System.out.println(line + "\n");
    }

    String getInputLine() throws NoSuchElementException {
        return sc.nextLine();
    }
}
