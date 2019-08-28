import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TaskList<T> extends ArrayList<T> implements Serializable {
    public  TaskList(){
        super();
    }
    public TaskList(Collection<? extends T> e){
        super(e);
    }
}
