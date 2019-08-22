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
                case "list": this.handleList();
                case "bye": this.handleBye();
                    return;  // exit
                default: this.handleDefault(command);
            }
        }
        sc.close();
    }

    private void handleBye() {
        Response.NewFarewell().print();
    }

    private void handleDefault(String command) {
        Response.NewEcho(command).print();
    }

    private void handleList() {
        for (int i = 0; i < this.taskList.size(); i++) {

            System.out.println();

        }
    }
}
