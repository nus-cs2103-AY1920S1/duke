public class Response {
    private String command;

    Response(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        if (!command.equals("bye")) {
            return String.format("    ____________________________________________________________\n"
                    + "     %s\n" + "    ____________________________________________________________\n", command);
        } else { //when bye is called
            return "    ____________________________________________________________\n"
                    + "     Bye. Hope to see you again soon!\n"
                    + "    ____________________________________________________________\n";
        }
    }
}
