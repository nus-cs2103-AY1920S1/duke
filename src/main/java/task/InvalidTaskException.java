package task;
import java.lang.Exception;

public class InvalidTaskException extends Exception {
    String result;
    public InvalidTaskException(String msg) {
        super(msg);
        this.result = msg;
    }

    public String toString() {
        return "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________";
    }
}
