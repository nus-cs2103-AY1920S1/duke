import java.lang.StringBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TaskList {

    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(Stream<String> lines) {
        this.taskList = new ArrayList<>();
        lines.forEach(line-> {
            try {
                taskList.add(FileLineParser.parse(line));
            } catch (LineInFileParseException lifpe) {
                new Ui().showLineError(lifpe.getLineCount(), line);
            }
        });
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public String delete(int index) {
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);
        return task.toString();
    }

    public String done(int index) {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        return task.toString();
    }

    public int size() {
        return taskList.size();
    }

    public String list() {
        StringBuffer buffer = new StringBuffer();
        int i = 1;
        for (Task task :  taskList) {
            buffer.append("    " + i + "." + task.toString() + "\n");
            i++;
        }
        return buffer.toString();
    }

    public Stream<String> save() {
        return taskList.stream().map(x->TaskParser.parse(x));
    }

}
