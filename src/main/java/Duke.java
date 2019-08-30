import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IncompleteCommandException, InvalidCommandException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();

        Action.loadTaskList(list);

        Action.welcomeMessage();

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            Action.doTask(command, list);
            command = sc.nextLine();
        }

        Action.byeMessage();
    }
}
