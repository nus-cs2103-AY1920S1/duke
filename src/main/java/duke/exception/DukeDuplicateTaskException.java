package duke.exception;

import java.io.IOException;

public class DukeDuplicateTaskException extends IOException {
    public DukeDuplicateTaskException() {
        super("This task has been added alr \uD83D\uDE2F");
    }
}
