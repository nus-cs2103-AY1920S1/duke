class FridayController implements ControllerInterface {
    private TaskModelInterface model;
    private Display view;

    public FridayController(TaskModelInterface model){
        this.model = model;
        view = new Display(this, model);
    }

    public void start(){
        this.view.instance();
    }

    public void stop(){
        //this.view.
    }
       
}
