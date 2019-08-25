import java.util.Scanner;

public class Ui {
    Scanner in = new Scanner(System.in);

    public void show(String str) {
        if (!str.endsWith("\n"))
            str += '\n';
        System.out.println("    ____________________________________________________________\n     " +
                str.replaceAll("\n(?!$)", "\n     ") +
                "    ____________________________________________________________\n");
    }

    public void showWelcome() {
        show("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        return in.nextLine();
    }
}
