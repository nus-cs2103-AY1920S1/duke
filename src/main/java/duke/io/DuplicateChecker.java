package duke.io;

import duke.actionstack.DukeActionStack;

public class DuplicateChecker {
    private static String[] WHITELIST = {"list", "done", "delete", "find", "clear"};
    private static boolean checkWhitelist(String act) {
        for(String white:WHITELIST) {
            if(white.contentEquals(act)) {
                return true;
            }
        }
        return false;
    }
    public static boolean checkDuplication(String act) {
        String command = act.split(" ")[0];
        if(checkWhitelist(command)) {
            return false;
        }
        for(String prev: DukeActionStack.getStack()) {
            if(prev.contentEquals(act)) {
                return true;
            }
        }
        return false;
    }
}
