package duke.task;

public class ToDoStub extends ToDo {

    public ToDoStub() {
        super.description = "read book";
        super.isDone = false;
    }


    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][\u2713] " + this.description; //shows tick
        } else {
            return "[T][\u2718] " + this.description; //shows cross
        }
    }

    @Override
    public String createTaskInFileFormat() {
        return super.createTaskInFileFormat();
    }
}
