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
        TaskInterface task = new TaskImplementation(command);
        this.model.addTask(task);
        this.display.printAddTaskSection(task.toString());
    }

}
