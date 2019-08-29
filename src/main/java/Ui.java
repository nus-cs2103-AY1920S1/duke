/**
 * UI of the application.
 */
public class Ui {
    /**
     * Shows Loading Error.
     */
    public void showLoadingError() {
        drawline();
        System.out.println("     Error");
        drawline();
    }
    /**
     * Shows welcome letter.
     */
    public void showWelcome() {
        drawline();
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?");
        drawline();
    }
    /**
     * Draws lines.
     */
    public static void drawline(){
        System.out.println("    ____________________________________________________________");
    }
    /**
     * Shows bye letter.
     */
    public static void bye(){
        drawline();
        System.out.println("     Bye. Hope to see you again soon!");
        drawline();
    }

}
