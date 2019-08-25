public class ToDoStub extends ToDo {

    public ToDoStub() { ;
        super.description = "read book";
        super.isDone = false;
    }


    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][\u2713] " + this.description;
        } else {
            return "[T][\u2718] " + this.description;
        }
    }

    @Override
    public String createTaskInFileFormat() {
        return super.createTaskInFileFormat();
    }
}
