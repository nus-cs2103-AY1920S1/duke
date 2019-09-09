import command.*;
import main.*;
import task.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    private Duke(){
        storage = new Storage();
        try {
            ArrayList<Task> list = storage.readFromFile();
            this.taskList = new TaskList(list);

        }   catch (FileNotFoundException fE) {
            System.err.println(fE);
        }
    }

    private void run() throws IOException {
        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String nextLine = sc.nextLine();

            Command c = parser.parse(nextLine);
            c.execute(taskList, storage);

            if(c instanceof ByeCommand) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        UI.start();
        try {
            duke.run();
        } catch (IOException ioE) {
            System.err.println(ioE);
        }
    }
}
