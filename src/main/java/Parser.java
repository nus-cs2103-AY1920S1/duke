import java.util.Scanner;

public class Parser {
    private TaskList tasklist;
    private Scanner sc;
    private Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.tasklist = taskList;
        sc = new Scanner(System.in);
        this.ui = ui;
    }

    public TaskList parse() {
        String input = sc.nextLine();
        boolean isTheEnd = false;

        while (!isTheEnd) {
            String[] taskDetails = input.split(" ");
            String taskType = taskDetails[0].trim();
            boolean isInValid = checkValidCommand(taskType);
            boolean isInComplete = false;

            if (isInValid) {
                while (isInValid) {
                    input = sc.nextLine();
                    String[] newTaskDetails = input.split(" ");
                    String newTaskType = newTaskDetails[0].trim();
                    isInValid = checkValidCommand(newTaskType);
                }
            }
            if (taskType.equals("list")) {
                ui.printTaskList();
                for (int i = 1; i <= tasklist.getTaskSize(); i++) {
                    System.out.println("" + i + ". " + tasklist.getTask(i - 1));
                }
                input = sc.nextLine();
            } else if (taskType.equals("done")) {
                isInComplete = checkIncompleteCommand(input, 2);
                if(isInComplete){
                    input = sc.nextLine();
                    continue;
                }
                isInValid = checkValidIndex(Integer.parseInt(taskDetails[1]), tasklist.getTaskSize());
                if(isInValid){
                    input = sc.nextLine();
                    continue;
                }
                int index = Integer.parseInt(taskDetails[1]) - 1;

                tasklist.getTask(index).markAsDone();
                ui.printMarkedTask(tasklist.getTask(index));
                input = sc.nextLine();

            } else if (taskType.equals("deadline")) {

                String[] msg = input.split("/by");
                isInComplete = checkIncompleteCommand(msg[0], 2);
                if(isInComplete){
                    input = sc.nextLine();
                    continue;
                }
                isInComplete = checkIncompleteCommand(msg[1], 2);
                if(isInComplete){
                    input = sc.nextLine();
                    continue;
                }
                tasklist.addTask(new Deadline(msg[0].trim().substring(8).trim(), msg[1].trim()));
                ui.printTaskAdded(tasklist.getTask(tasklist.getTaskSize() - 1), tasklist.getTaskSize());
                input = sc.nextLine();

            } else if (taskType.equals("event")) {

                String[] msg = input.split("/at");
                isInComplete = checkIncompleteCommand(msg[0], 2);
                if(isInComplete){
                    input = sc.nextLine();
                    continue;
                }
                isInComplete = checkIncompleteCommand(msg[1], 2);
                if(isInComplete){
                    input = sc.nextLine();
                    continue;
                }
                tasklist.addTask(new Event(msg[0].trim().substring(5).trim(), msg[1].trim()));
                ui.printTaskAdded(tasklist.getTask(tasklist.getTaskSize() - 1), tasklist.getTaskSize());
                input = sc.nextLine();

            } else if (taskType.equals("todo")) {
                checkIncompleteCommand(input, 2);
                String toDo = input.substring(5);
                tasklist.addTask(new ToDo(toDo));
                ui.printTaskAdded(tasklist.getTask(tasklist.getTaskSize() - 1), tasklist.getTaskSize());
                input = sc.nextLine();

            } else if (input.equals("bye")) {
                isTheEnd = true;
                ui.showGoodbye();

            } else if (taskType.equals("delete")) {
                isInComplete = checkIncompleteCommand(input, 2);
                if(isInComplete){
                    input = sc.nextLine();
                    continue;
                }
                isInValid = checkValidIndex(Integer.parseInt(taskDetails[1]), tasklist.getTaskSize());
                if(isInValid){
                    input = sc.nextLine();
                    continue;
                }
                int index = Integer.parseInt(taskDetails[1]) - 1;

                ui.printRemoveTask(tasklist.getTask(index), tasklist.getTaskSize() - 1);
                tasklist.deleteList(index);
                input = sc.nextLine();
            }
        }
        return tasklist;
    }

    private boolean checkIncompleteCommand(String description, int expectedLength) {
        try {
            if (description.split(" ").length < expectedLength) {
                throw new IncompleteCommandException("The description of the command is incomplete\n" +
                        "Please enter again.");
            }
            return false;
        } catch (IncompleteCommandException e) {
            System.out.println(e);
            return true;
        }
    }

    public boolean checkValidIndex(int index, int taskSize) {
        try {
            if (index < 1 || index > tasklist.getTaskSize()) {
                throw new IndexOffBoundException();
            }
            return false;
        } catch (IndexOffBoundException e) {
            System.out.println(e);
            return true;
        }
    }

    public boolean checkValidCommand(String taskType) {
        try {
            switch (taskType) {
            case "list":
                break;
            case "deadline":
                break;
            case "event":
                break;
            case "delete":
                break;
            case "bye":
                break;
            case "todo":
                break;
            case "done":
                break;
            default:
                throw new InvalidCommandException("I'm sorry, but I don't know what that means :-( \n" +
                        "Enter a command again:");
            }
            return false;
        } catch (InvalidCommandException e) {
            System.out.println(e);
            return true;
        }
    }
}
