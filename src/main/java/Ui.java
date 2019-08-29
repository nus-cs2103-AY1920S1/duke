import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readLine() {
        return sc.nextLine();
    }

    public void printWelcomeMsg() {
        printLine();
        System.out.println("\tHello! I'm Duke!");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    public void printExitMsg() {
        printLine();
        System.out.println("\tBye! Hope to see you again soon!");
        printLine();
    }

    public void printErrorMsg(DukeException e) {
        printErrorLine();
        System.out.println("\t" + e.getMessage());
        printErrorLine();
    }

    public void printSentence(String sentence) {
        System.out.println("\t" + sentence);
    }

    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void printErrorLine() {
        System.out.println("\t************************************************************");
    }
}
