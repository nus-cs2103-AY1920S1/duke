public class Duke {

    private Ui ui;
    private TaskList taskList;

    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    private void run() {
        this.ui.showWelcomeMessage();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(this.taskList, this.ui);
                if (command.getCommandType().equals(CommandType.EXIT)) {
                    break;
                }
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

/*
    public static void main(String[] args){
        //program start
        startMessage();


        //import scanner + logic
        Scanner scanner = new Scanner(System.in);

        while (true){
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");

            if (tokens[0].equals("bye")){
                break;
            } else if (tokens[0].equals("list")){
                printList();
            } else if (tokens[0].equals("done")){
                try {
                    doneTask(Integer.parseInt(tokens[1]));
                }
                catch (ArrayIndexOutOfBoundsException error){
                    new DukeException("Missing task", DukeExceptionType.MISSINGTASK).printError();
                }
                catch (IllegalArgumentException error2){
                    new DukeException("Task has been done", DukeExceptionType.TASKALREADYDONE).printError();
                }
                catch (IndexOutOfBoundsException error3){
                    new DukeException("No such task", DukeExceptionType.TASKNOTFOUND).printError();
                }
            } else if (tokens[0].equals("delete")){
                try {
                    deleteTask(Integer.parseInt(tokens[1]));
                }
                catch (IndexOutOfBoundsException error){
                    new DukeException("No such task", DukeExceptionType.TASKNOTFOUND).printError();
                }
            } else if (tokens[0].equals("todo")){
                try {
                    addToDo(input.substring(5));
                }
                catch (StringIndexOutOfBoundsException error){
                    new DukeException("Empty description", DukeExceptionType.MISSINGDESCRIPTION).printError();
                }

            } else if (tokens[0].equals("deadline")){
                try {
                    String[] dateSplit = input.split(" /by ");
                    String deadlineDesc = dateSplit[0].substring(9);
                    checkValidInput("deadline",input);
                    addDeadline(deadlineDesc, dateSplit[dateSplit.length - 1]);
                }
                catch (StringIndexOutOfBoundsException error){
                    new DukeException("Empty description", DukeExceptionType.MISSINGDESCRIPTION).printError();
                }
                catch (IllegalArgumentException error2){
                    new DukeException("Missing date", DukeExceptionType.MISSINGDATE).printError();
                }
            } else if (tokens[0].equals("event")){
                try {
                    String[] dateSplit = input.split(" /at ");
                    String eventDesc = dateSplit[0].substring(6);
                    checkValidInput("event",input);
                    addEvent(eventDesc, dateSplit[dateSplit.length - 1]);
                }
                catch (StringIndexOutOfBoundsException error){
                    new DukeException("Empty description", DukeExceptionType.MISSINGDESCRIPTION).printError();
                }
                catch (IllegalArgumentException error2){
                    new DukeException("Missing date", DukeExceptionType.MISSINGDATE).printError();
                }
            } else {
                new DukeException("Invalid Command", DukeExceptionType.INVALIDCOMMAND).printError();
            }
        }
        exitMessage();
    }

    private static void addDeadline(String task, String by){
        System.out.println(HORIZONTAL_LINE);
        System.out.println(formatText("Got it. I've added this task:"));
        Deadline deadlineTask = new Deadline(task,by );
        taskList.add(deadlineTask);
        System.out.println(formatText("  " + deadlineTask ));
        System.out.println(formatText("Now you have " + taskList.size() + " tasks in the list."));
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addEvent(String task, String at){
        System.out.println(HORIZONTAL_LINE);
        System.out.println(formatText("Got it. I've added this task:"));
        Event eventTask = new Event(task, at);
        taskList.add(eventTask);
        System.out.println(formatText("  " + eventTask ));
        System.out.println(formatText("Now you have " + taskList.size() + " tasks in the list."));
        System.out.println(HORIZONTAL_LINE);
    }

    private static void doneTask(int index) throws IllegalArgumentException, IndexOutOfBoundsException{
        if (taskList.get(index-1).getIsDone()){
            throw new IllegalArgumentException("Task has already been done");
        }
        taskList.get(index-1).toggleDone();
        System.out.println(HORIZONTAL_LINE);
        System.out.println(formatText("Nice! I've marked this task as done:"));
        System.out.println(formatText("  " + taskList.get(index-1)));
        System.out.println(HORIZONTAL_LINE);
    }

    private static void deleteTask(int index) throws IndexOutOfBoundsException{
        Task toRemove = taskList.get(index-1);
        taskList.remove(index - 1);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(formatText("Noted. I've removed this task:"));
        System.out.println(formatText("  " + toRemove));
        System.out.println(formatText("Now you have " + taskList.size() + " tasks in the list."));
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printList(){
        System.out.println(HORIZONTAL_LINE);
        System.out.println(formatText("Here are the tasks in your list:"));
        int index = 1;
        for (Task task: taskList){
            System.out.println(formatText(index + "." + task.toString()));
            index++;
        }
        System.out.println(HORIZONTAL_LINE);
    }
    private static String formatText(String text) {
        return "     " + text;
    }

    private static void startMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(formatText("Hello! I'm Duke"));
        System.out.println(formatText("What can I do for you?"));
        System.out.println(HORIZONTAL_LINE);
    }


    private static void checkValidInput(String command, String input) throws IllegalArgumentException{
        switch(command){
        case "deadline":
            if (!input.contains(" /by ")){
                throw new IllegalArgumentException("Missing deadline");
            }
            break;
        case "event":
            if (!input.contains(" /at ")){
                throw new IllegalArgumentException("Missing deadline");
            }
            break;
        }
    }
*/
