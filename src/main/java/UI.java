package duke;

import java.util.Scanner;

public class UI {
    private static Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    /**
     * Shows error about why file cannot be read.
     */
    public void showLoadingError(Exception e) {
        System.out.println(e);
        System.out.println(e.getCause());
    }

    /**
     * Main event loop.
     */
    public void run() {
        boolean status = true;
        String in;
        while (status) {
            try {
                in = sc.next();
            } catch (Exception e) {
                break; // input stream has ended.
            }
            try {
                status = Parser.handleCommand(in, sc);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}
