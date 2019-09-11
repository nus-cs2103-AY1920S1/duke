package duke.actionstack;

import java.util.LinkedList;

public class DukeActionStack {
    private static LinkedList<String> actionStack;
    public DukeActionStack() {
        actionStack = new LinkedList<>();
    }

    public static LinkedList<String> getStack() {
        return actionStack;
    }

    public static String retrieveAction() {
        return actionStack.pop();
    }

    public static void pushAction(String act) {
        actionStack.push(act);
    }
}
