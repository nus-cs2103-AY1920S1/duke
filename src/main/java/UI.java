import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class UI {
    protected PrintStream ps;

    public UI() {
        try {
            this.ps = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            System.out.println("UI: Something serious happened here...");
        }
    }

    public void echo(String s) {
        ps.println(s);
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
