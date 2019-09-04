public class Command {

    private boolean isExit;
    private String cmd;
    private String cmdDetails;

    public Command(String firstPart, String everythingElse) {
        this.cmd = firstPart;
        this.cmdDetails = everythingElse;
    }


    public void execute(TaskList list, Ui ui, Storage store) {
        switch(cmd) {
            case "bye":
                this.isExit = false;
                break;

            case "list":
                list.printAll();
                break;

            case "delete":
                list.removeTask(Integer.parseInt(cmdDetails));
                break;

            case "done":
                list.doTask();
                store.updateFile(list);
                break;

            case "todo":
                if(cmdDetails.length < 1) {
                    throw new DukeException();
                } else {
                    Tasks newTodo = new Todo(cmdDetails);
                    list.addTask(newTodo);
                    store.updateFile(list);
                    Ui.printAddedMsg()
                }
                break;

            case "deadline":
                String[] separate = cmdDetails.split("/");
                try {
                    Tasks newDeadline = new Deadline(separate[0].trim(), separate[1].trim());
                    list.addTask(newDeadline);
                    store.updateFile(list);
                    Ui.printAddedMsg();
                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new DukeException();
                }
                break;

            case "event":
                String[] separate2 = cmdDetails.split("/");
                try{
                    Tasks newEvent = new Event(separate2[0].trim(), separate2[1].trim());
                    list.addTask(new Event);
                    store.updateFile(list);
                    Ui.printAddedMsg();
                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new DukeException();
                }
                break;
        }
    }

    public boolean isExit() {
        return this.isExit;
    }
}