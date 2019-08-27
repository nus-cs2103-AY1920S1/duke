import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {

    private MessageGenerator greeter;
    private UI ui;
    private Scanner sc;
    private boolean isExit;

    public Duke() {
        greeter = new MessageGenerator();
        sc = new Scanner(System.in);
        isExit = false;
    }

    public void run() {
        greeter.greet();
        while (sc.hasNextLine() && !isExit) {
        ui = new UI(sc.nextLine());
        ui.processCommand(ui.processInput());
    }
        greeter.bye();
}

    public static void main(String[] args) {
        new Duke().run();
    }
}

