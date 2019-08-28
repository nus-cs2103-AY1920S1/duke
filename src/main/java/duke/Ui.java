package duke;

import duke.DukeException;

import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.String.format;

public class Ui {
    private String line;
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
        this.line = "\t____________________________________________________________\n";
    }

    /**
     * Error printer if duke.DukeException is encountered.
     *
     * @param e Caught duke.DukeException.
     */
    public void showLoadingError(DukeException e) {
        System.out.println(this.line + format("\t %s\n", e) + this.line);
    }

    public String readCommand() {
        String s = sc.nextLine();
        return s;
    }

    /**
     * printing the given input to the interface.
     * @param args iteration of string inputs that each represent a line to be printed.
     */
    public void printStatement(String... args) {
        String content = format("%s", Stream.of(args).map(s -> "\t " + s + "\n")
                .reduce((x,y) -> x + y).orElse(""));
        System.out.println(this.line + content + this.line);
    }
}
