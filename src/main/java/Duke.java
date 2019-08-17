public class Duke {
    public static void main(String[] args) {
        /* TODO: init friday and model */

        TaskModelInterface model = new BasicTaskModel();
        ControllerInterface friday = new FridayController(model);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        friday.start();
    }
}
