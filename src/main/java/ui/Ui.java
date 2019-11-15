package ui;

import command.Command;
import util.Parser;
import util.exception.DukeException;

import java.util.Scanner;

public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String LINE = "______________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String welcome() {
        return ("Hello from\n" + LOGO + "\nWhat can I do for you ?");
    }

    public Command getUserInput() throws DukeException {
        String input = this.scanner.nextLine();
        return Parser.parse(input);
    }

    public void printResponse(String response) {
        System.out.println(encase(response));
    }

    public void printErrorMessage(DukeException e) {
        System.out.println(encase(e.getMessage()));
    }

    public String encase(String message) {
        return String.join("\n", new String[]{ LINE, message, LINE });
    }
}
