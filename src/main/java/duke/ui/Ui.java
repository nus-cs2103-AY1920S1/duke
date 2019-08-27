package duke.ui;

import java.util.Scanner;

public class Ui {
    private static Ui ui = null;
    private Scanner sc;

    private Ui() {
        this.sc = new Scanner(System.in);
    }

    public static Ui getInstance() {
        if (ui == null) {
            ui = new Ui();
        }
        return ui;
    }

    public String getNextLine() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        }

        throw new IllegalStateException();
    }

    public void printLine(Object output) {
        System.out.println(output);
    }
}