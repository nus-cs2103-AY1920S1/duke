import java.util.Scanner;

public class Duke {

    private MessageGenerator greeter;
    private UI ui;
    private Scanner sc;
    private boolean isExit;

    public Duke() {
        greeter = new MessageGenerator();
        sc = new Scanner(System.in);
        ui = new UI("files/tasks.txt");
        isExit = false;
    }

    public void run() {
        greeter.greet();
        while (!isExit) {
            ui.processInput();
            ui.processCommand();
            isExit = ui.isExit();
            System.out.println(ui.isExit());
        }
        greeter.bye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

