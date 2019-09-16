public class Command {

    private boolean isExit;
    private String cmd;
    private String cmdDetails;

    public Command(String firstPart, String everythingElse) {
        this.cmd = firstPart;
        this.cmdDetails = everythingElse;
    }


    public String execute(TaskList list, Ui ui, SaveToFile store) throws DukeException {
        switch (cmd) {
        case "bye":
            //System.out.println("Bye! See you again soon!");
            return "Bye! See you again soon!";
            //this.isExit = true;
            //break;

        case "list":
            return list.printAll();
            //break;

        case "delete":
            if (cmdDetails.length() < 1) {
                throw new DukeException("Please specify the task to be deleted.");
            } else {
                list.deleteTask(Integer.parseInt(cmdDetails) - 1);
                store.updateFile(list);
                return "Task deleted!";
            }
            //break;

        case "done":
            if (cmdDetails.length() < 1) {
                throw new DukeException("Please specify the task that is completed.");
            } else {
                int taskIndex =  Integer.parseInt(cmdDetails);
                list.getTask(taskIndex - 1).doTask();
                store.updateFile(list);
                return "Task done";
            }
            //break;

        case "todo":
            if (cmdDetails.length() < 1) {
                throw new DukeException("The details of todo cannot be blank.");
            } else {
                Tasks newTodo = new Todo(cmdDetails);
                list.addTask(newTodo);
                store.updateFile(list);
                return Ui.printAddedMsg();
                //return "Todo added";
            }
            //break;

        case "deadline":
            String[] separate = cmdDetails.split("/");
            try {
                Tasks newDeadline = new Deadline(separate[0].trim(), separate[1].trim());
                list.addTask(newDeadline);
                store.updateFile(list);
                return Ui.printAddedMsg();
                //return "Deadline added!";
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS! I do not know what that means!");
            }
            //break;

        case "event":
            String[] separate2 = cmdDetails.split("/");
            try {
                Tasks newEvent = new Event(separate2[0].trim(), separate2[1].trim());
                list.addTask(newEvent);
                store.updateFile(list);
                return Ui.printAddedMsg();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS! I do not know what that means!");
            }
            //break;

        default:
            throw new DukeException("OOPS! I do not know what that means!");
        }
    }

    public boolean isExit() {
        return this.isExit;
    }
}