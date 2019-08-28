public class Todo extends Task {
	/**
	 * Constructor.
	 * @param description Description.
	 */
	public Todo(String description) {
        super(description);
    }

    /**
     * Shows task.
     */
    @Override
    public String showTask() {
        return "[T]" + super.showTask();
    }
    
    /**
     * Shows task as its saving format.
     */
    @Override
    public String toSave() {
    	if (super.isDone) {
    		return "donetodo " + super.description;
    	} else {
    		return "todo " + super.description;
    	}
    }
}
