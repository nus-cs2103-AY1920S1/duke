import java.util.Scanner;
import java.io.IOException;

public class ToDoList {

    public void run() throws IOException {

        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        Storage storage = new Storage("./todoList.txt");
        TaskList task = new TaskList(storage.fileInitialization());

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] temp = input.split(" ");
            while (temp.length == 0) {
                ui.printMessage("Please input something :(");
                input = sc.nextLine();
                temp = input.split(" ");
            }
            Command c = Parser.parse(input);
            c.execute(task, ui, storage);
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            new ExitCommand().execute(task, ui, storage);
        }
    }
}
