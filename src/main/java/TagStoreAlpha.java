//package tagModule;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class TagStoreAlpha extends AbstractTagStore<TaskInterface>
    implements AddTagObserver, QueryTagObserver, 
    QueryTaskObserver, DeletePairObserver, DeleteTaskObserver,
    DeleteTagObserver {

    /* before use, please register with TagCommandPost */
    //UItaskfeedback, UItagfeedback, UIpairfeedback, UIquery

    /* NOTE: AbstractTagStore doesnt support all ops yet */
    //private TaskModelInterface tasklist;

    public TagStoreAlpha() { //TaskModelInterface tasklist) {
        super();
        //this.tasklist = tasklist;
    }

    public String tagCode(TaskInterface task) {
        return task.getSaveFormat();
    }

    public void addTagUpdate(String tag, TaskInterface task) {
        super.addTaggedItem(tag, task);
    }

    public void deleteTagUpdate(String tag) {
        super.deleteTag(tag);
    }            

    public void deleteTaskUpdate(TaskInterface task) {
        super.deleteItem(task);
    }

    public void queryTagUpdate(String tag) {
    }

    public void queryTaskUpdate(TaskInterface task) {
    }

    public void deletePairUpdate(String tag, TaskInterface task) {
    }
}
