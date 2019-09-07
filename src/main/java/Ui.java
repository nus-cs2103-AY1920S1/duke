import java.util.Scanner;

class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String LINE = "______________________________";
    private Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    String welcome() {
        return ("Hello from\n" + LOGO + "\nWhat can I do for you ?");
    }

    Command getUserInput() throws DukeException {
        String[] input = this.scanner.nextLine().trim().split("\\s+");
        return Parser.parse(input);
    }

    void printResponse(String response) {
        System.out.println(encase(response));
    }

    void printErrorMessage(DukeException e) {
        System.out.println(encase(e.getMessage()));
    }

    String encase(String message) {
        return String.join("\n", new String[]{ LINE, message, LINE });
    }
}
