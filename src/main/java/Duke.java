public class Duke {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();

        ui.printBlock("Hello! I'm Duke\n" +
                "What can I do for you?");
        ui.println();

        while (true) {
            final String input = ui.nextLine();

            if (input.equals("bye")) {
                ui.printBlock("Bye. Hope to see you again soon!");
                break;
            } else {
                ui.printBlock(input);
                ui.println();
            }
        }
    }
}
