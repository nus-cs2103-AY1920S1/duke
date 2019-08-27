import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {
    public void initMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private Storage storage;

    Ui(Storage storage) {
        this.storage = storage;
    }

    static Scanner sc = new Scanner(System.in);

    void showLoadingError() {
        System.out.println("Task list not retrieved.");
    }

    void readUserInput() {
        while(sc.hasNext()) {
            String act = sc.next();
                try {
                    Parser.parse(act, storage);
                } catch (DukeIllegalDescriptionException e) {
                    System.out.println(e.getMessage());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
    }
}
