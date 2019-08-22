import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<String> taskList;

    public Duke() {
        this.taskList = new ArrayList<>();
    }


    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);

        // start interaction
        Response.NewGreetings().print();

        while (sc.hasNext()) {
            String command = sc.nextLine();
            switch (command) {
                case "list":
                    this.handleList();
                    break;
                case "bye":
                    this.handleBye();
                    return;  // exit
                default:
                    this.handleAddItem(command); // add item to list
            }
        }
        sc.close();
    }

    private void handleBye() {
        Response.NewFarewell().print();
    }

    private void handleAddItem(String item) {
        this.taskList.add(item);
        Response.NewAdded(item).print();
    }

    private void handleList() {
        Response.NewListing(this.taskList).print();
    }
}
