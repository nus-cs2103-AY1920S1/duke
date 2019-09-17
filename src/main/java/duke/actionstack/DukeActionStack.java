package duke.actionstack;

import java.util.LinkedList;

public class DukeActionStack {
    private static LinkedList<String> actionStack;

    /**
     * Constructor of DukeActionStack.
     */
    public DukeActionStack() {
        actionStack = new LinkedList<>();
    }

    public static LinkedList<String> getStack() {
        return actionStack;
    }

    /**
     * Retrieves the latest action.
     * @return the most recent action String
     */
    public static String retrieveAction() {
        return actionStack.pop();
    }

    /**
     * Pushes an action to the stack.
     * @param act Action string to be pushed
     */
    public static void pushAction(String act) {
        actionStack.push(act);
    }
}
