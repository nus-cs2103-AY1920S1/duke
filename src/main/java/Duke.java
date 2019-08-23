import java.util.ArrayList;
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
        Response.newGreetings().print();

        while (sc.hasNext()) {
            try {
                Command command = Command.NewCommand(sc.nextLine());
                switch (command.getType()) {
                case LIST:
                    this.handleList();
                    break;
                case DONE:
                    this.handleDone(command.getTargetIndex());
                    break;
                case DELETE:
                    this.handleDelete(command.getTargetIndex());
                    break;
                case ADD:
                    this.handleAddItem(command.getAddedTask());
                    break;
                case BYE:
                    this.handleBye();
                    return;  // exit
                case ECHO:
                    this.handleEcho(command.toString());
                default:
                    // placeholder
                }
            } catch (CommandException e) {
                Response.newException(e).print();
            }

        }
        sc.close();
    }

    private void handleDone(int targetIndex) {
        this.taskList.get(targetIndex).setCompleted(true);
        Response.newDone(this.taskList.get(targetIndex)).print();
    }

    private void handleDelete(int targetIndex) {
        Task removed = this.taskList.remove(targetIndex);
        Response.newDelete(removed, this.taskList.size()).print();
    }

    private void handleBye() {
        Response.newFarewell().print();
    }

    private void handleEcho(String command) {
        Response.newEcho(command).print();
    }

    private void handleAddItem(Task item) {
        this.taskList.add(item);
        Response.newAdded(item, this.taskList.size()).print();
    }

    private void handleList() {
        Response.newListing(this.taskList).print();
    }

}
