class Todo extends Task {
    public Todo(String task) {
        super(task);
    }
 
    public String toStore() {
        if(super.isCompleted()) {
            return "T | 1 | " + super.toString();
        } else {
            return "T | 0 | " + super.toString();     
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}