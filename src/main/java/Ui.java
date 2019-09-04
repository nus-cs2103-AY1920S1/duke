import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    public void showLoadingError() {
    	System.out.println("    ____________________________________________________________");
        System.out.println("     No task to load");
        System.out.println("    ____________________________________________________________\n");
    }

    public void showLine() {
    	System.out.println("    ____________________________________________________________\n");
    }

    public void showError(String errorMessage) {
        System.out.println("     " + errorMessage);
    }

    public String readCommand() {
    	Scanner sc = new Scanner(System.in);
    	return sc.nextLine();
    }
}
