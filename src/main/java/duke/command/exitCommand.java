package duke.command;

public class exitCommand extends command {
    public String exit() {
        return printLine() +
                "      Bye. Hope to see you again soon!\n" +
                printLine() +
                "\n";
    }

    private String printLine() {
        return "    ____________________________________________________________\n";
    }
}
