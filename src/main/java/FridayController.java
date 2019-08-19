// java -Dfile.encoding=UTF8 classname
import java.util.Iterator;
class FridayController implements ControllerInterface {
    private TaskModelInterface model;
    private Display display;
    private TaskCreator taskCreator;

    public FridayController(TaskModelInterface model) {
        this.model = model;
        this.display = new Display(this, model);
        this.taskCreator = new BasicTaskCreator();
    }

    public void start() {
        this.display.instance();
    }

    public void stop() {
        //this.display.
    }

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

    public void doneTask(String command) {
        String[] commandlist = command.split(" ");
        /* check for exceptions */
        Integer taskNum = Integer.valueOf(commandlist[1]);
        TaskInterface task = this.model.doneTask(taskNum);
        this.display.printDoneTaskSection(task.toString());
    }

    public void deleteTask(String command) {
        String[] commandlist = command.split(" ");
        /* check for exceptions */
        Integer taskNum = Integer.valueOf(commandlist[1]);
        TaskInterface task = this.model.deleteTask(taskNum);
        this.display.printDeleteTaskSection(task.toString(), 
                this.model.getTotalTasks());
    }

    public void listTasks() {
        Iterator<TaskInterface> iter = 
            this.model.getTaskListIterator();

        this.display.printAllTasks(iter);
    }
}
