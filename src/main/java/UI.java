import java.util.StringJoiner;

class UI {
    public void showWelcomeMessage() {
        StringJoiner sj = new StringJoiner("\n");
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        sj.add("Hello from\n" + logo);
        sj.add("What can I do for you?");
        showMessage(sj.toString());
    }

    public void showByeMessage() {
        String exitMessage = "Bye. Hope to see you again soon!";
        showMessage(padMessage(exitMessage));
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    private String padMessage(String message) {
        StringJoiner result = new StringJoiner("\n");
        result.add("____________________________________________________________");
        result.add(message);
        result.add("____________________________________________________________");
        return result.toString();
    }
}