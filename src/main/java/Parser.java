// java -Dfile.encoding=UTF8 classname
import java.util.stream.Stream;
import java.util.Iterator;


/**
 * Class which contains logic to deterime what commands to be executed
 * given an input by user
 */
class Parser implements ControllerInterface {
    private TaskModelInterface model;
    private Ui display;
    private TaskCreator taskCreator;

    public Parser(TaskModelInterface model) {
        this.model = model;
        //this.display = new Display(this, model);
        this.display = new Ui(this, model);
        this.taskCreator = new BasicTaskCreator();
    }

    /**
     * Returns void, call UI to generate UI and start session
     *  for user
     */
    public void start() {
        this.display.instance();
    }

    public void stop() {
        //this.display.
    }

    /**
     * Returns nothing, this command evaluates user command
     *   and executes what needs to be done
     *   individual command relies on TaskCreator implementation
     *
     * @param command Input user command
     * @return void
     */
    public void whatsGoingOn(String command) {
        String[] commandlist = command.split(" ");
        if (commandlist[0].toUpperCase().equals("LIST")) {
            this.listTasks();
        } else if 
            (commandlist[0].toUpperCase().equals("DONE")) {
            this.doneTask(command);
        } else if 
            (commandlist[0].toUpperCase().equals("DELETE")) {
            this.deleteTask(command);
        } else {
            this.addTask(command);
        }
    }

    /**
     * Returns boolean, allows customization of how to end
     *   the OWO program to be used by Ui
     *  @param cmd Command text to test if it matches preselected
     *    command to end OWO program
     *   @return boolean if input matches preselected bye command
     */
    public boolean isEndCommand(String cmd) {
        String[] cmdlist = cmd.split(" ");
        return cmdlist[0].toUpperCase().equals("BYE");
    }

    /**
     * Returns void, interprets and calls requisite classes and housekeeping methods
     * when a new Task is to be added to TaskList
     * @param command Command input by user to be passed here to be interpreted
     */
    public void addTask(String command) {

        try {
            TaskInterface task = taskCreator.createTask(command);
            this.model.addTask(task);
            this.display.printAddTaskSection(task.toString(), 
                    this.model.getTotalTasks());
                        
        } catch (OWOException ex) {
            this.display.printErrorSection(ex.getMessage());
        }            
                
    }

    /**
     * Returns void, interprets and calls requisite classes and housekeeping methods
     * when a new Task is to be marked as done
     * @param command Command input by user to be passed here to be interpreted
     */
    public void doneTask(String command) {
        String[] commandlist = command.split(" ");
        /* check for exceptions */
        Integer taskNum = Integer.valueOf(commandlist[1]);
        TaskInterface task = this.model.doneTask(taskNum);
        this.display.printDoneTaskSection(task.toString());
    }

    /**
     * Returns void, interprets and calls requisite classes and housekeeping methods
     * when a new Task is to be deleted from tasklist
     * @param command Command input by user to be passed here to be interpreted
     */
    public void deleteTask(String command) {
        String[] commandlist = command.split(" ");
        /* check for exceptions */
        Integer taskNum = Integer.valueOf(commandlist[1]);
        TaskInterface task = this.model.deleteTask(taskNum);
        this.display.printDeleteTaskSection(task.toString(), 
                this.model.getTotalTasks());
    }

    /**
     * Returns void, calls requisite classes methods to print a list of all
     * tasks in tasklist
     */
    public void listTasks() {
//        Iterator<TaskInterface> iter = 
//            this.model.getTaskListIterator();
        Stream<TaskInterface> taskStream = 
            this.model.getTaskStream();

        //this.display.printAllTasks(iter);
        this.display.printAllTasks(taskStream);
    }
}
