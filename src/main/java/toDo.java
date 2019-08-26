public class ToDo extends Task {

    final String TASK_TYPE = "[T]";

    public ToDo(String description) {
        super(description);
    }

    public String getType() {
        return TASK_TYPE;
    }

    public String getDescription(){
        return this.description;
    }

    @Override
    public String toString() {
        return TASK_TYPE + super.getStatusIcon() + " " +  super.toString();
    }

}
