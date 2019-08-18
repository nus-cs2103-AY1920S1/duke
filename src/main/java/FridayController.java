import java.util.Iterator;
class FridayController implements ControllerInterface {
    private TaskModelInterface model;
    private Display display;

    public FridayController(TaskModelInterface model) {
        this.model = model;
        display = new Display(this, model);
    }

    public void start() {
        this.display.instance();
    }

    public void stop() {
        //this.display.
    }

    public void addTask(String command) {
        // L2: task only has name
        // L2.2: simple factory to get task
//        TaskInterface task = new TaskImplementation(command);
        TaskInterface task = TaskImplementation.makeTask(command);
        this.model.addTask(task);
        this.display.printAddTaskSection(task.toString());
    }

    public void doneTask(String command) {
        String[] commandlist = command.split(" ");
        /* check for exceptions */
        Integer taskNum = Integer.valueOf(commandlist[1]);
        TaskInterface task = this.model.doneTask(taskNum);
        this.display.printDoneTaskSection(task.toString());
    }

    public void listTasks() {
        Iterator<TaskInterface> iter = 
            this.model.getTaskListIterator();

        this.display.printAllTasks(iter);
    }
}
