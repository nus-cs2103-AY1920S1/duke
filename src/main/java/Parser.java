/**
 * Parser processes the user commands and carries out the specific functions on it
 */
public class Parser {
/*
    /**
     * Class Attributes
     */
    //private Storage storage;
    //private TaskList tasks;
    //private Ui ui;


    /**
     * Class Constructor
     */
    public Parser(/*Storage storage, TaskList tasks, Ui ui*/){
        //this.storage = storage;
        //this.tasks = tasks;
        //this.ui = ui;
    }

    /**
     * Interprets the command and calls the executing class
     * @param command
     * @return the command as an object
     */
    public static Command parse(String command){
        String[]words = command.split(" ");
        if (command.equals("bye")) {                                                                                    //IF BYE
            return new ByeCommand(command);
        } else if ( (words.length==2) && (words[0].equals("done")) && (isNumeric(words[1])) ) {                         //IF DONE
            return new DoneCommand(command);
        } else if ((words.length==2)&&(words[0].equals("delete"))&&(isNumeric(words[1]))){                              //IF DELETE
            return new DeleteCommand(command);
        } else if (command.equals("list")){
            return new ListCommand(command);
        } else if(words[0].equals("find")){
            return new FindCommand(command);
        } else {
            return new TaskCommand(command);
        }

    }

/*
    /**
     * Prints introduction, takes in user input, and processes it
     * @throws FileNotFoundException
     */
    /*public void run() throws FileNotFoundException {

        final String LINE_BORDER = "____________________________________________________________";
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            String[]words = command.split(" ");
            System.out.println(LINE_BORDER);

            if (command.equals("bye")) {                                                                                //IF BYE
                System.out.println("Bye. Hope to see you again soon!" + "\n" + LINE_BORDER);
                System.exit(0);
                break;
            } else if (command.equals("EmptyOutput")) {
                this.EmptyOutput();
            } else if ( (words.length==2) && (words[0].equals("done")) && (isNumeric(words[1])) ) {                     //IF DONE
                this.done(command);
            } else if ((words.length==2)&&(words[0].equals("delete"))&&(isNumeric(words[1]))){                          //IF DELETE
                this.delete(command);
            } else if (command.equals("list")){                                                                         //IF LIST
                tasks.printForList();
            } else if(words[0].equals("find")){
                this.find(command);
            } else {
                try {
                    String[]splitWords = command.trim().split("\\s",2);

                    if (splitWords[0].equals("todo")) {                                                                 //IF TODO
                        this.createTodo(command);
                    } else if (splitWords[0].equals("deadline")) {                                                      //IF DEADLINE
                        this.createDeadline(command);
                    } else if (splitWords[0].equals("event")) {                                                         //IF EVENT
                        this.createEvent(command);
                    } else {
                        throw new IllegalArgumentException();
                    }

                    System.out.println("Got it. I've added this task:" + "\n" + tasks.printLatest()
                            + "\n" + "Now you have " + tasks.size() + " tasks in the list.");

                } catch (IllegalArgumentException e){
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
                } catch (DukeException e){
                    System.out.println("☹ OOPS!!! The date format is wrong.");
                } catch (Exception e){
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                }
            }
            System.out.println(LINE_BORDER);
        }
    }




    /**
     * clears the DukeOutput in case anything goes wrong, but does not clear list
     * @throws FileNotFoundException
     */
/*    public void EmptyOutput() throws FileNotFoundException {
        storage.emptyOutput();
        System.out.println("Output Emptied");
    }


    /**
     * marks tasks as done and prints the output
     * @param command is the string input that is processed
     */
/*    public void done(String command){
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            tasks.taskDone(val-1);
            System.out.println("Nice! I've marked this task as done: \n" + tasks.taskPrint(val-1));
            storage.printToOutput(tasks);
        } catch (Exception e) {
            System.out.println("Error, you have entered an invalid number");
        }
    }

/*
    /**
     * deletes the task from TaskList
     * @param command is the string input that is processed
     */
 /*   public void delete(String command){
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            System.out.println("Noted. I've removed this task:"+ "\n" + tasks.taskPrint(val-1) +
                    "\n"+ "Now you have " + (tasks.size()-1) + " tasks in the list.");
            tasks.remove(val - 1);
            storage.printToOutput(tasks);
        } catch (Exception e) {
            System.out.println("Error, you have entered an invalid number");
        }
    }


    /**
     * finds for the search results use inputs
     * @param command is the user input string
     */
 /*   public void find(String command){
        String[]splitWords = command.split(" ",2);
        String wordToFind = splitWords[1];
        TaskList findResults = new TaskList();

        for(int i = 0; i < tasks.size(); i++){
            String taskCommand = tasks.get(i).getCommand();
            if (taskCommand.contains(wordToFind) ){
                findResults.add(tasks.get(i));
            }else{}
        }
        if(findResults.isEmpty()){
            System.out.println("Sorry, we couldn't find any results!");
        }else{
            System.out.println("Here are the matching tasks in your list:");
            findResults.printForList();
        }

    }


    /**
     * Creates a new ToDo task
     * @param command is the user string input to be processed
     * @throws Exception in case user inputs in an incorrect format
     */
 /*   public void createTodo(String command) throws Exception {
        String[]splitWords = command.trim().split("\\s",2);
        String midCommand = splitWords[1].trim();

        if (midCommand.length() != 0) {
            tasks.add(new ToDo(midCommand));
            storage.printToOutput(tasks);
        } else {
            throw new Exception();
        }
    }


    /**
     * Creates a new deadline task
     * @param command is the user string input to be processed
     * @throws Exception in case user inputs in an incorrect format
     */
 /*   public void createDeadline(String command) throws Exception {
        String[]splitWords = command.trim().split("\\s",2);
        String midCommand = splitWords[1].trim();

        if (midCommand.length() != 0) {
            tasks.add(new Deadline(midCommand));
            storage.printToOutput(tasks);
        } else {
            throw new Exception();
        }
    }


    /**
     * Creates a new event task
     * @param command is the user string input to be processed
     * @throws Exception in case user inputs in an incorrect format
     */
 /*   public void createEvent(String command) throws Exception {
        String[]splitWords = command.trim().split("\\s",2);
        String midCommand = splitWords[1].trim();

        if (midCommand.length() != 0) {
            tasks.add(new Event(midCommand));
            storage.printToOutput(tasks);
        } else {
            throw new Exception();
        }
    }
*/

    /**
     * determines whether parameter is an integer
     * @param str takes in the string that will be checked
     * @return boolean value
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
