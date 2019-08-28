public class Todo extends Task {
	public Todo(String description) {
        super(description);
    }

    @Override
    public String showTask() {
        return "[T]" + super.showTask();
    }
    
    @Override
    public String toSave() {
    	if (super.isDone) {
    		return "donetodo " + super.description;
    	} else {
    		return "todo " + super.description;
    	}
    }
}
