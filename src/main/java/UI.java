public class UI {

    protected boolean isRunning;

    public UI() {
        this.isRunning = true;
    }

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello I'm\n" + logo + "\nWhat can I do for you?");
    }

    public void goodbye() {

        System.out.println("Bye. Hope to see you again soon!");
        this.isRunning = false;

    }




}
