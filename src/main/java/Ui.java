import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);
    private String hLine = "    ____________________________________________________________";

    public void showWelcome() {
        System.out.println(hLine);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.printf("%s\n\n", hLine);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showOpeningLine() {
        System.out.println(hLine);
    }

    public void showClosingLine() {
        System.out.printf("%s\n\n", hLine);
    }

    public void showError(String err) {
        System.out.println(err);
    }

    public void showLoadingError(String err) {
        System.out.println(hLine);
        System.out.printf("     %s\n", err);
        System.out.printf("%s\n\n", hLine);
    }

    public void showSavingError(String err) {
        showLoadingError(err);
    }

}
