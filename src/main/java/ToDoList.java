import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class ToDoList {

    public void run() throws IOException {

        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        Storage storage = new Storage("./todoList.txt");

        TaskList task = new TaskList(storage.fileInitialization());

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            Parser.parse(input, ui, task, storage);
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            ui.printBye();
        }
    }
}
