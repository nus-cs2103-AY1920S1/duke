public class Ui {
    private static String divider = "    " + "-".repeat(61);

    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeRespond("Hello! I'm Duke", "What can I do for you?");
    }

    public void dukeRespond(String... inputs) {
        System.out.println(divider);
        for (String str : inputs) {
            System.out.println("     " + str);
        }
        System.out.println(divider);
    }

    public void showLoadingError() {

    }
}
