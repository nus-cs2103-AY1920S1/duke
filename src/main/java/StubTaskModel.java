import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;
class StubTaskModel implements TaskModelInterface {

    public void initialize() {}
    public void registerObserver(TaskObserver obs) {}
    public void removeObserver(TaskObserver obs) {}
    public int getTotalTasks() { return 0; }
    public void addTask(TaskInterface task) {}
    public TaskInterface doneTask(int refNum) { return null; }
    public TaskInterface deleteTask(int refNum) { return null; }
    public Iterator<TaskInterface> getTaskListIterator() { return null; }
    public Stream<TaskInterface> getTaskStream() { 
        List<TaskInterface> xs = new ArrayList<>();
        xs.add(new DeadLinesImplementation("Summon Cthulhu", 
            "innsmouth", true));
        xs.add(new EventsImplementation("Halloween Party", 
            "day after friday 13th", false));
        xs.add(new ToDosImplementation("Photocopy Necronomicon", 
            true));
        return xs.stream();
    }

}

