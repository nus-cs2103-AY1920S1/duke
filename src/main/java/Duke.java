import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");

        MainManager mm = new MainManager();
        try {
            mm.readFromFile();
        } catch (FileNotFoundException fE) {
            System.out.println(fE);
        }
        mm.run();
    }
}
