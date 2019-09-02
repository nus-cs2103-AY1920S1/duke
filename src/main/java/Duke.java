import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");

        Scanner input = new Scanner(System.in);
        String user = input.nextLine();
        Parser parser;
        Storage saveFile = new Storage();

        while (!user.equals("bye")) {
            TaskList taskList = new TaskList(saveFile.LoadData());
            parser = new Parser(user);
            try {
                switch (parser.getCommandType()) {
                case LIST:
                    taskList.listTasks();
                    break;
                case COMPLETE:
                    taskList.completeTask(parser.getDescription());
                    break;
                case ADDTODO:
                    taskList.addTodo(parser.getDescription(),parser.isDone());
                    taskList.printnewtask();
                    break;
                case ADDDEADLINE:
                    taskList.addDeadline(parser.getDescription(),parser.isDone(),parser.getDate());
                    taskList.printnewtask();
                    break;
                case ADDEVENT:
                    taskList.addEvent(parser.getDescription(),parser.isDone(),parser.getDate());
                    taskList.printnewtask();
                    break;
                case DELETE:
                    taskList.removeTask(parser.getDescription());
                    break;
                default:
                    System.out.println("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "    ____________________________________________________________");
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! The description of a task cannot be empty.\n" +
                        "    ____________________________________________________________");
            }
            saveFile.StoreData(taskList.getTaskList());
            user = input.nextLine();
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
