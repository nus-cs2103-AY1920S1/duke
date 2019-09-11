package duke.io;

import duke.actionstack.DukeActionStack;

public class DuplicateChecker {
    public static boolean checkDuplication(String act) {
        for(String prev: DukeActionStack.getStack()) {
            if(prev.contentEquals(act)) {
                return true;
            }
        }
        return false;
    }
}
