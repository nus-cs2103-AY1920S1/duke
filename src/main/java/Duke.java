import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input = "";
        Scanner sc = new Scanner(System.in);

        printLogo();
        printIntro();

        do {
            if (!sc.hasNextLine()) {
                continue;
            }

            input = sc.nextLine();

            if (input.equals("bye")) {
                printExitMsg();
            } else {
                printResponse(input);
            }
        } while (!input.equals("bye"));
    }

    //prints the logo for the chat bot
    private static void printLogo() {
        System.out.println(Consts.LOGO);
    }

    //prints the indentation used for the output
    private static void printIndentation() {
        System.out.print("\t");
    }

    //prints line with indentation in front
    private static void printLine() {
        printIndentation();
        System.out.println("____________________________________________________________");
    }

    //prints the intro text
    private static void printIntroText() {
        printIndentation();
        System.out.println(" Hello! I'm Duke");
        printIndentation();
        System.out.println(" What can I do for you?");
    }

    private static void printIntro() {
        printLine();
        printIntroText();
        printLine();
        System.out.println();
    }

    private static void printExitMsg() {
        printLine();
        printIndentation();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    private static void printResponse(String response) {
        printLine();
        printIndentation();
        System.out.println(" " + response);
        printLine();
        System.out.println();
    }
}
