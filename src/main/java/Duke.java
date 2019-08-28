import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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
        TaskList taskList = new TaskList();
        String user = input.nextLine();

        String[] cases = {"list","done","todo","deadline","event","delete"};

        while (!user.equals("bye")) {
            int i = 0;
            for(i = 0; i < cases.length; i++) {
                if (user.contains(cases[i])) break;
            }
            try {
                switch (i) {
                case 0:
                    taskList.listTasks();
                    break;
                case 1:
                    taskList.completeTask(user);
                    break;
                case 2:
                    taskList.addTodo(user.split(" ", 2)[1], "true");
                    break;
                case 3:
                    taskList.addDeadline(user.split(" ", 2)[1]);
                    break;
                case 4:
                    taskList.addEvent(user.split(" ", 2)[1]);
                    break;
                case 5:
                    taskList.removeTask(user);
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
            user = input.nextLine();
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
