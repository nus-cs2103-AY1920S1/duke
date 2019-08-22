import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<Task> taskList;

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
            Command command = Command.NewCommand(sc.nextLine());
            switch (command.getType()) {
                case LIST:
                    this.handleList();
                    break;
                case DONE:
                    this.handleDone(command.getTargetIndex());
                    break;
                case ADD:
                    this.handleAddItem(command.getAddedTask());
                    break;
                case BYE:
                    this.handleBye();
                    return;  // exit
                default:
                    // placeholder

            }
        }
        sc.close();
    }

    private void handleDone(int targetIndex) {
        this.taskList.get(targetIndex).setCompleted(true);
        Response.NewDone(this.taskList.get(targetIndex)).print();
    }

    private void handleBye() {
        Response.NewFarewell().print();
    }

    private void handleAddItem(String item) {
        this.taskList.add(new Task(item));
        Response.NewAdded(item).print();
    }

    private void handleList() {
        Response.NewListing(this.taskList).print();
    }
}
