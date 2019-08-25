public class UI {

    private static String divider = "\t____________________________________________________________";

    public UI() {

    }

    public void showWelcome() {
        this.showLine();
        String logo = "\t   ____        _        \n"
                + "\t  |  _ \\ _   _| | _____ \n"
                + "\t  | | | | | | | |/ / _ \\\n"
                + "\t  | |_| | |_| |   <  __/\n"
                + "\t  |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\t  Hello! I'm Duke");
        System.out.println("\t  What can I do for you?");
        this.showLine();
    }

    public void showLine() {
        System.out.println(divider);
    }

    public void showLoadingError() {
        System.out.println("Unable to load previous data! A new task list is created.");
    }

    public void showLoadingSuccessful() {
        System.out.println("Previous task list loaded successfully.");
    }
}
