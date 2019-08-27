class Ui {
    void numberErrorMessage() {
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! Please type in a valid index from 1 to 100\n" +
                "    ____________________________________________________________\n");
    }

    void indexErrorMessage(int len) {
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! Index out of bounds for task list of length " + len + "\n" +
                "    ____________________________________________________________\n");
    }

    //Greet the user when starting up Duke
    void greet() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        printLine();
        System.out.println(logo);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        printLine();
        printLine();
        System.out.println("     Here are the tasks that you have now:");
    }

    // Echo commands entered by users
    void echo(String command) {
        printLine();
        System.out.println("     " + command);
        printLine();
        System.out.println();
    }

    //Exits when the user types bye
    void exit() {
        printLine();
        System.out.println("      Bye. Hope to see you again soon!");
        printLine();
        System.out.println();
    }

    private void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
