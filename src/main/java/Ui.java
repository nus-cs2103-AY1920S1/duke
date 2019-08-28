public class Ui {
    public void showLoadingError() {
        drawline();
        System.out.println("     Error");
        drawline();
    }

    public void showWelcome() {
        drawline();
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?");
        drawline();
    }
    public static void drawline(){
        System.out.println("    ____________________________________________________________");
    }
    public static void bye(){
        drawline();
        System.out.println("     Bye. Hope to see you again soon!");
        drawline();
    }

}
