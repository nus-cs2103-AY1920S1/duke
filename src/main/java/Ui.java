public class Ui {
    public void welcomeMessage() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        drawLine();
        System.out.println("    Hello from\n" + logo);
        System.out.println("    What can I do for you?");
        drawLine();
        System.out.println("\n");
    }

    public void printErrorMessage(String e) {
        drawLine();
        System.out.println("     " + e);
        drawLine();
        System.out.println("\n");
    }

    public void byeMessage() {
        drawLine();
        System.out.println("     Bye. Hope to see you again soon!");
        drawLine();
        System.out.println("\n");
    }

    public void drawLine() {
        String line = "    ________________________"
                + "____________________________________";
        System.out.println(line);
    }

    public void drawLineNewLine() {
        String line = "    ________________________"
                + "____________________________________";
        System.out.println(line);
        System.out.println();
    }
}
